package joke.k.myapplication.login.drawer.fragments.databaseFragment.showSavedJoke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import joke.k.myapplication.R;
import joke.k.myapplication.login.JokeApplication;
import joke.k.myapplication.login.data.RandomJokes;

public class ShowSavedJokeActivity extends AppCompatActivity implements ShowSavedJokeContract.View {

    @BindView(R.id.toolbar_in_saved_joke)
    Toolbar toolbar;

    @BindView(R.id.anserwJokesFromDatabase)
    TextView anserwFromDatabase;

    @BindView(R.id.askJokesFromDatabase)
    TextView askFromDatabase;

    @Inject
    ShowSavedJokeContract.Presenter presenter;

    public static final String JOKE_ID_KEY = "Joke id";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_saved_joke);
        ButterKnife.bind(this);
        ((JokeApplication) getApplication()).getAppComponent()
                .plus(new ShowSavedJokeModule(this))
                .inject(this);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());




        presenter.handleJoke(getIntent().getIntExtra(JOKE_ID_KEY,-1));
    }

    @Override
    public void sendJokeFromDatabase(RandomJokes jokeById) {
    anserwFromDatabase.setText(jokeById.getPunchline());
    askFromDatabase.setText(jokeById.getSetup());
    }
}
