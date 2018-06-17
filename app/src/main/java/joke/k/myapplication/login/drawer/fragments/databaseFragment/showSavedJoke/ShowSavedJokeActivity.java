package joke.k.myapplication.login.drawer.fragments.databaseFragment.showSavedJoke;

import android.app.Activity;
import android.os.Bundle;

import joke.k.myapplication.R;

public class ShowSavedJoke extends Activity {
    public static final String JOKE_ID_KEY = "Joke id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_saved_joke);

    }
}
