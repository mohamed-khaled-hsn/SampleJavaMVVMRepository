package m.khaled.samples.di.module;

import dagger.Module;
import dagger.Provides;
import m.khaled.samples.instructors.model.remote.InstructorService;
import retrofit2.Retrofit;

import javax.inject.Singleton;

/**
 * Created by Mohamed Khaled on Thu, 09/Aug/2018 at 1:01 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    public InstructorService providesInstructorService(Retrofit retrofit) {
        return retrofit.create(InstructorService.class);
    }

}
