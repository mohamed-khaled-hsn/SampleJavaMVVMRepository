package m.khaled.samples.helpers.livedata;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import m.khaled.samples.helpers.AppExecutors;


/**
 * A generic class that can provide a resource backed by the network only .
 *
 * @param <ResultType>
 * @param <RequestType>
 */
public abstract class NetworkOnlyResource<ResultType, RequestType> {

    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();
    private final AppExecutors appExecutors;

    @MainThread
    public NetworkOnlyResource(AppExecutors appExecutors) {
        result.setValue(Resource.loading(null));
        this.appExecutors = appExecutors;
        fetchFromNetwork();
    }

    @MainThread
    private void setValue(Resource<ResultType> newValue) {
        if (!(result.getValue() != null && result.getValue().equals(newValue))) {
            appExecutors.mainThread().execute(() -> result.setValue(newValue));
        }
    }

    private void fetchFromNetwork() {
        LiveData<ApiResponse<RequestType>> apiResponse = createCall();
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            //noinspection ConstantConditions
            if (response != null && response.isSuccessful()) {
                appExecutors.diskIO().execute(() -> {
                    RequestType requestType = processResponse(response);
                    ResultType resultType = processResult(requestType);
                    appExecutors.mainThread().execute(() ->
                            setValue(Resource.success(resultType))
                    );
                });

            } else {
                appExecutors.diskIO().execute(() -> {
                    RequestType requestType = processResponse(response);
                    ResultType resultType = processResult(requestType);
                    if (response != null) {
                        appExecutors.diskIO().execute(() ->
                                setValue(Resource.error(response.getErrorMessage(), response.getCode(), resultType)));
                    } else
                        appExecutors.diskIO().execute(() ->
                                setValue(Resource.error("Error, something happened", 500, resultType)));

                    onFetchFailed();
                });

            }
        });
    }

    protected void onFetchFailed() {
    }

    public LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }

    @WorkerThread
    protected RequestType processResponse(ApiResponse<RequestType> response) {
        return response.body;
    }

    @WorkerThread
    protected abstract ResultType processResult(@Nullable RequestType result);


    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> createCall();
}