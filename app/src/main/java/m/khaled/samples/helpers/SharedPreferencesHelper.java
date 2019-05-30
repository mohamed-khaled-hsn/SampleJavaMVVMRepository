package m.khaled.samples.helpers;

import android.content.SharedPreferences;
import androidx.annotation.Nullable;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Created by Mohamed Khaled on Thu, 09/Aug/2018 at 1:01 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
@Singleton
public class SharedPreferencesHelper {
    public static final String PREF_NAME = "zevent_prefs";

    private static final String USER_TOKEN = "user_token";

    private static final String DEVICE_TOKEN = "device_token";
    private static final String LANGUAGE_CODE = "language_code";
    private static final String LANGUAGE_ID = "id";
    private static final String USER_LOGGED_IN = "user_logged_in";
    private static final String TRACK_ID = "current_event_id";
    private static final String USER_ID = "user_id";
    private static final String TRACKS_COUNT = "tracks_count";

    private SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferencesHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void clearSharedPreferences() {
        sharedPreferences.edit().clear().apply();
    }

    /**
     * @return userId saved in prefs, default value is -1
     */
    public int getUserId() {
        return sharedPreferences.getInt(USER_ID, -1);
    }

    public void setUserId(int userId) {
        sharedPreferences.edit().putInt(USER_ID, userId).apply();
    }

    @Nullable
    public String getUserToken() {
        return sharedPreferences.getString(USER_TOKEN, null);
    }

    public void setUserToken(String userToken) {
        sharedPreferences.edit().putString(USER_TOKEN, userToken).apply();
    }

    @Nullable
    public String getDeviceToken() {
        return sharedPreferences.getString(DEVICE_TOKEN, null);
    }

    public void setDeviceToken(String deviceToken) {
        sharedPreferences.edit().putString(DEVICE_TOKEN, deviceToken).apply();
    }

    @Nullable
    public String getDeviceType() {
        return "1";
    }

    @Nullable
    public String getLanguageCode() {
        return sharedPreferences.getString(LANGUAGE_CODE, null);
    }

    @Nullable
    public String getLanguageId() {
        return sharedPreferences.getString(LANGUAGE_ID, null);
    }

    public boolean isUserLoggedIn() {
        return sharedPreferences.getBoolean(USER_LOGGED_IN, false);
    }

    public void setUserLoggedIn(boolean isUserLoggedIn) {
        sharedPreferences.edit().putBoolean(USER_LOGGED_IN, isUserLoggedIn).apply();
    }

    /**
     * @return current event id, default value is -1 if not found
     */
    public int getTrackId() {
        return sharedPreferences.getInt(TRACK_ID, -1);
    }

    public void setTrackId(int trackId) {
        sharedPreferences.edit().putInt(TRACK_ID, trackId).apply();
    }

    public int getTracksCount() {
        return sharedPreferences.getInt(TRACKS_COUNT, 1);
    }

    public void setTracksCount(int userId) {
        sharedPreferences.edit().putInt(TRACKS_COUNT, userId).apply();
    }

}
