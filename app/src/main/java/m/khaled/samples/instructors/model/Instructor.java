package m.khaled.samples.instructors.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohamed Khaled on Wed, 17/Oct/2018 at 2:29 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */

@Entity(tableName = "Instructor")
public class Instructor implements Parcelable {
    @PrimaryKey
    @SerializedName("instructor_id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("name")
    private String name;

    @SerializedName("photo")
    private String photoUrl;

    public Instructor(int id) {
        this.id = id;
    }

    public static final Creator<Instructor> CREATOR = new Creator<Instructor>() {
        @Override
        public Instructor createFromParcel(Parcel in) {
            return new Instructor(in);
        }

        @Override
        public Instructor[] newArray(int size) {
            return new Instructor[size];
        }
    };

    protected Instructor(Parcel in) {
        id = in.readInt();
        title = in.readString();
        name = in.readString();
        photoUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(name);
        dest.writeString(photoUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}



