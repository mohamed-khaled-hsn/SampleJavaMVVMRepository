package m.khaled.samples.di.module;

import android.app.Application;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import m.khaled.samples.AppDatabase;
import m.khaled.samples.instructors.model.local.InstructorDao;

import javax.inject.Singleton;

/**
 * Created by Mohamed Khaled on Thu, 09/Aug/2018 at 1:01 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
@Module
public class DaoModule {

    @Provides
    @Singleton
    public AppDatabase provideAppDatabase(Application app) {
        return Room.databaseBuilder(app,
                AppDatabase.class, "mentor_coe_localDB.db")
                .fallbackToDestructiveMigration()
                .build();
    }


    @Provides
    @Singleton
    public InstructorDao provideInstructorDao(AppDatabase appDatabase) {
        return appDatabase.instructorDao();
    }


}
