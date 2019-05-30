package m.khaled.samples.di;

import dagger.Component;
import m.khaled.samples.di.module.ApiModule;
import m.khaled.samples.di.module.AppModule;
import m.khaled.samples.di.module.DaoModule;
import m.khaled.samples.di.module.NetModule;
import m.khaled.samples.instructors.viewmodel.InstructorViewModel;

import javax.inject.Singleton;

/**
 * Created by Mohamed Khaled on Thu, 09/Aug/2018 at 1:01 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                NetModule.class,
                DaoModule.class,
                ApiModule.class
        }
)
public interface AppComponent {

    void inject(InstructorViewModel instructorViewModel);

}
