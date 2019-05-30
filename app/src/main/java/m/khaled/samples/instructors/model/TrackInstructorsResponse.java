package m.khaled.samples.instructors.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mohamed Khaled on Wed, 17/Oct/2018 at 7:45 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
@Entity(tableName = "Instructors")
public class TrackInstructorsResponse {
    @PrimaryKey
    @ColumnInfo(name = "TrackId")
    private int trackId;

    @ColumnInfo(name = "InstructorList")
    @TypeConverters(TrackInstructorsResponseConverter.class)
    @SerializedName("instructors")
    private List<Instructor> instructorList;

    public TrackInstructorsResponse(int trackId, List<Instructor> instructorList) {
        this.trackId = trackId;
        this.instructorList = instructorList;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public List<Instructor> getInstructorList() {
        return instructorList;
    }

    public void setInstructorList(List<Instructor> instructorList) {
        this.instructorList = instructorList;
    }
}
