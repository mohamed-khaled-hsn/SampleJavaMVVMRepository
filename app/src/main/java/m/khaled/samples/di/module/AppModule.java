package m.khaled.samples.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import static m.khaled.samples.helpers.SharedPreferencesHelper.PREF_NAME;

/**
 * Created by Mohamed Khaled on Thu, 09/Aug/2018 at 1:01 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
@Module
public class AppModule {
    final private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreference(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

}
