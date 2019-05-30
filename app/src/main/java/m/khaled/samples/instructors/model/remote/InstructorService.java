package m.khaled.samples.instructors.model.remote;

import androidx.lifecycle.LiveData;
import m.khaled.samples.helpers.livedata.ApiResponse;
import m.khaled.samples.instructors.model.TrackInstructorsResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Mohamed Khaled on Thu, 09/Aug/2018 at 1:01 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
public interface InstructorService {

    @GET("tracks-instructors/{trackId}")
    LiveData<ApiResponse<TrackInstructorsResponse>> getInstructorsList(@Path("trackId") int trackId);



}