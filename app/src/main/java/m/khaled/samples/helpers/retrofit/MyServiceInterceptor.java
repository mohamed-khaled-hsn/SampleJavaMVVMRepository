package m.khaled.samples.helpers.retrofit;

import android.util.Log;
import androidx.annotation.NonNull;
import m.khaled.samples.helpers.SharedPreferencesHelper;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

/**
 * Created by Mohamed Khaled on Sun, 12/Aug/2018 at 2:55 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */

/**
 * Interceptor which adds headers from shared preferences according to the added custom headers,
 * Authentication, languageCode and level headers by default.
 * <br>
 * when No-Authentication or Single-Language header is set to true add Authentication and multi
 * language headers from prefs
 */
@Singleton
public class MyServiceInterceptor implements Interceptor {
    @Inject
    SharedPreferencesHelper sharedPreferencesHelper;
    private String userToken;
    private String languageCode;
    private String languageId;
    private Request.Builder requestBuilder;

    @Inject
    MyServiceInterceptor() {
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        requestBuilder = request.newBuilder();
        Log.i("MyServiceInterceptor", "in MyServiceInterceptor " + request.headers().get("No-Authentication"));
        if (request.header("No-Authentication") == null || "false".equalsIgnoreCase(request.headers().get("No-Authentication"))) {
            addAuthenticationHeader();
            requestBuilder.removeHeader("No-Authentication");

        } else
            requestBuilder.removeHeader("No-Authentication");
        if (request.header("Single-Language") == null || "false".equalsIgnoreCase(request.headers().get("Single-Language"))) {
            addLanguageCodeHeader();
            addLanguageIdHeader();
            requestBuilder.removeHeader("Single-Language");

        } else
            requestBuilder.removeHeader("Single-Language");

        return chain.proceed(requestBuilder.build());
    }

    private void addAuthenticationHeader() {
        if (userToken == null) {
            userToken = sharedPreferencesHelper.getUserToken();

            if (userToken == null)
                Log.e("MyServiceInterceptor", "Null user token in API call that requires authentication");
            else
                requestBuilder.addHeader("Authorization", "Bearer {" + userToken + "}");

        } else {
            requestBuilder.addHeader("Authorization", "Bearer {" + userToken + "}");
        }
    }

    private void addLanguageCodeHeader() {
        if (languageCode == null) {
            languageCode = sharedPreferencesHelper.getLanguageCode();
            if (languageCode == null)
                Log.e("MyServiceInterceptor", "Null language code in API call that requires language");
            else
                requestBuilder.addHeader("Language-Code", languageCode);

        } else
            requestBuilder.addHeader("Language-Code", languageCode);
    }

    private void addLanguageIdHeader() {
        if (languageId == null) {
            languageId = sharedPreferencesHelper.getLanguageId();

            if (languageId == null)
                Log.e("MyServiceInterceptor", "Null language Id in API call that requires language");
            else
                requestBuilder.addHeader("languageid", languageId);

        } else
            requestBuilder.addHeader("languageid", languageId);
    }


}