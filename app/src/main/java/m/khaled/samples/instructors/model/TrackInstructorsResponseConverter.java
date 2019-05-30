package m.khaled.samples.instructors.model;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Mohamed Khaled on Thu, 18/Oct/2018 at 4:22 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
public class TrackInstructorsResponseConverter {
    private Gson gson = new Gson();
    private Type type = new TypeToken<List<Instructor>>() {
    }.getType();

    @TypeConverter
    public String fromInstructorsList(List<Instructor> instructorList) {
        if (instructorList == null) {
            return (null);
        }

        return gson.toJson(instructorList, type);
    }

    @TypeConverter
    public List<Instructor> toInstructorsList(String instructorsListString) {
        if (instructorsListString == null) {
            return (null);
        }
        return gson.fromJson(instructorsListString, type);
    }
}
