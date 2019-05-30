package m.khaled.samples.instructors.model.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import m.khaled.samples.instructors.model.TrackInstructorsResponse;

@Dao
public interface InstructorDao {
    @Query("SELECT * FROM Instructors WHERE TrackId= :trackId")
    LiveData<TrackInstructorsResponse> getInstructorList(int trackId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInstructorList(TrackInstructorsResponse trackInstructorsResponse);

}
