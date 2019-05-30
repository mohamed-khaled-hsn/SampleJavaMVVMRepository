package m.khaled.samples.helpers.retrofit;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import m.khaled.samples.helpers.livedata.ApiResponse;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A Retrofit adapter that converts the Call into a LiveData of {@link ApiResponse}.
 *
 * @param <R>
 */
final class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<ApiResponse<R>>> {
    private final Type responseType;

    public LiveDataCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }


    public LiveData<ApiResponse<R>> adapt(@NonNull final Call<R> call) {
        return new LiveData<ApiResponse<R>>() {
            AtomicBoolean started = new AtomicBoolean(false);

            @Override
            protected void onActive() {
                super.onActive();
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(@NonNull Call<R> call, Response<R> response) {
                            postValue(new ApiResponse<>(response));
                        }

                        @Override
                        public void onFailure(@NonNull Call<R> call, Throwable throwable) {
                            postValue(new ApiResponse<R>(throwable));
                        }
                    });
                }
            }
        };
    }
}