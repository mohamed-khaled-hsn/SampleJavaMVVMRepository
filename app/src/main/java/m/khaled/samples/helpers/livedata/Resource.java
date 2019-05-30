package m.khaled.samples.helpers.livedata;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import m.khaled.samples.App;
import m.khaled.samples.R;
import m.khaled.samples.helpers.NetworkUtils;

import java.lang.ref.WeakReference;

import static m.khaled.samples.helpers.livedata.Resource.Status.*;

/**
 * Wrapper class to wrap any type of data that comes from the network or database and provides status values
 *
 * @param <T>
 */
public class Resource<T> {

    @Nullable
    public final String message;
    @NonNull
    private final Status status;

    @Nullable
    private final T data;
    private final int responseCode;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        if (status == SUCCESS) {
            WeakReference<Context> contextWeakReference =
                    new WeakReference<>(App.get().getApplicationContext());
            if (!NetworkUtils.isNetworkAvailable(contextWeakReference.get())) {
                //cached
                this.message = contextWeakReference.get()
                        .getResources().getString(R.string.network_error);
                this.responseCode = 500;
            } else {
                this.message = message; //from network
                this.responseCode = 200;
            }

        } else if (status == ERROR) { //status is error or loading
            this.message = message;
            this.responseCode = -1;
        } else {
            this.responseCode = 500;
            this.message = message;
        }
        this.status = status;
        this.data = data;
    }

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message, int responseCode) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.responseCode = responseCode;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String errorMsg, @Nullable T data) {
        return new Resource<>(ERROR, data, errorMsg);
    }

    public static <T> Resource<T> error(String errorMsg, int responseCode, @Nullable T data) {
        return new Resource<>(ERROR, data, errorMsg, responseCode);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    @NonNull
    public Status getStatus() {
        return status;
    }

    public int getResponseCode() {
        return responseCode;
    }

    @Nullable
    public T getData() {
        return data;
    }

    public enum Status {
        SUCCESS, ERROR, LOADING
    }
}