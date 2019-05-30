package m.khaled.samples;

import android.app.Application;
import android.content.Context;
import m.khaled.samples.di.AppComponent;
import m.khaled.samples.di.DaggerAppComponent;
import m.khaled.samples.di.module.ApiModule;
import m.khaled.samples.di.module.AppModule;
import m.khaled.samples.di.module.DaoModule;
import m.khaled.samples.di.module.NetModule;
import com.bumptech.glide.request.target.ImageViewTarget;

public class App extends Application {
    private static AppComponent appComponent;
    private static App instance;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static Context get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ImageViewTarget.setTagId(R.id.glide_custom_view_target_tag);
        appComponent = DaggerAppComponent.builder().
                appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .netModule(new NetModule(BuildConfig.BASE_URL))
                .daoModule(new DaoModule())
                .build();
    }
}
