package m.khaled.samples.di.module;

import android.app.Application;
import android.util.Log;
import m.khaled.samples.BuildConfig;
import m.khaled.samples.helpers.Logger;
import m.khaled.samples.helpers.retrofit.LiveDataCallAdapterFactory;
import m.khaled.samples.helpers.retrofit.MyServiceInterceptor;
import m.khaled.samples.helpers.retrofit.NullOnEmptyConverterFactory;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import javax.inject.Singleton;

/**
 * Created by Mohamed Khaled on Thu, 09/Aug/2018 at 1:01 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
@Module
public class NetModule {
    final private String baseUrl;

    public NetModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    public Cache provideHttpCache(Application application) {
        long cacheSize = 10 * 1024 * 1024L; //10 MB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setPrettyPrinting();
        return gsonBuilder.create();
    }

    /**
     * @param cache
     * @param myServiceInterceptor injected in MyServiceInterceptor class using constructor injection
     * @param interceptor
     * @return
     */
    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Cache cache, MyServiceInterceptor myServiceInterceptor, HttpLoggingInterceptor interceptor) {
        Log.i("NetModule", "Providing OkHttpClient");
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(myServiceInterceptor);
        builder.addNetworkInterceptor(interceptor);
        builder.cache(cache);
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Logger.d("providing new Retrofit");
        return new Retrofit.Builder()
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(ScalarsConverterFactory.create()) //the ordering is importing, we must but ScalersConverter before Gson
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        if (BuildConfig.DEBUG)
            return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        else
            return new HttpLoggingInterceptor();
    }
}
