package joke.k.myapplication.login.data;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsManager {

    private SharedPreferences sharedPreferences;
    public static final String PREFS_FILE_NAME = "joke.k.myapplication.PREFS_FILE";

    private SharedPreferences sharedPreferances;

    public PrefsManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
    }
}
