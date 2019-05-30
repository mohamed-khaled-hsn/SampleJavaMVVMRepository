package m.khaled.samples.instructors.model.repo;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import m.khaled.samples.helpers.AppExecutors;
import m.khaled.samples.helpers.SharedPreferencesHelper;
import m.khaled.samples.helpers.ShouldFetch;
import m.khaled.samples.helpers.livedata.ApiResponse;
import m.khaled.samples.helpers.livedata.NetworkBoundResource;
import m.khaled.samples.helpers.livedata.Resource;
import m.khaled.samples.instructors.model.Instructor;
import m.khaled.samples.instructors.model.TrackInstructorsResponse;
import m.khaled.samples.instructors.model.local.InstructorDao;
import m.khaled.samples.instructors.model.remote.InstructorService;


import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by Mohamed Khaled on Thu, 09/Aug/2018 at 1:01 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
@Singleton
public class InstructorRepository {


    private final InstructorService webService;
    @Inject
    SharedPreferencesHelper sharedPreferencesHelper;
    private AppExecutors appExecutors;
    private InstructorDao dao;

    @Inject
    public InstructorRepository(InstructorService instructorService, InstructorDao dao) {
        appExecutors = new AppExecutors();
        webService = instructorService;
        this.dao = dao;
    }

    public LiveData<Resource<List<Instructor>>> getInstructorsList() {
        final int trackId = sharedPreferencesHelper.getTrackId();
        return new NetworkBoundResource<List<Instructor>, TrackInstructorsResponse>(appExecutors) {
            @Override
            protected void onFetchFailed() {
                loadFromDb();
            }

            @Override
            protected void saveCallResult(@NonNull TrackInstructorsResponse item) {
                item.setTrackId(trackId);
                dao.insertInstructorList(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Instructor> data) {
                return ShouldFetch.networkRecommended(); //TODO check if cached
            }

            @NonNull
            @Override
            protected LiveData<List<Instructor>> loadFromDb() {
                return Transformations.map(dao.getInstructorList(trackId)
                        , trackInstructorsResponse -> {
                            if (trackInstructorsResponse != null)
                                return trackInstructorsResponse.getInstructorList();
                            else
                                return null;
                        });
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<TrackInstructorsResponse>> createCall() {
                return webService.getInstructorsList(trackId);
            }
        }.asLiveData();
    }

}
