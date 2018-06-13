package com.myapp.k.myapp.jokes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.myapp.k.myapp.JokesApplication;
import com.myapp.k.myapp.R;
import com.myapp.k.myapp.api.Api;
import com.myapp.k.myapp.data.RandomJokes;
import com.myapp.k.myapp.drawer.DrawerActivity;
import com.myapp.k.myapp.fragments.databaseFragment.DatabaseFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class JokesActivity extends AppCompatActivity implements JokesContract.View {

    public static final String NOTIFICATION_CHANNEL_ID = "channelId";
    public static final String NOTIFICATION_NAME = "Channel";


    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.askJokes)
    TextView textForAskJokes;

    @BindView(R.id.anserwJokes)
    TextView textForAnserwJokes;

    @BindView(R.id.joke_progress)
    ProgressBar jokeProgress;

    @BindView(R.id.joke_toolbar)
    Toolbar jokeToolbar;

    private Api api;
    private JokesContract.Presenter presenter;
    private List<RandomJokes> jokes = new ArrayList<>();
    private DatabaseFragmentAdapter jokesAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);
        ButterKnife.bind(this);

        setSupportActionBar(jokeToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.joke_App);
            getSupportActionBar().setTitle("");
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        presenter = new JokesPresenter(this,
                retrofit.create(Api.class), JokesApplication.getRoom().jokesDao()

        );


    }

    @Override
    public void showData(RandomJokes randomJokes) {
        textForAskJokes.setText(randomJokes.getSetup());
        textForAnserwJokes.setText(randomJokes.getPunchline());
        jokeProgress.setVisibility(View.INVISIBLE);
    }


    @OnTouch
    @Override
    public boolean swapTouch(MotionEvent event) {
        presenter.onSwapDetected(event);
        return true;
    }

    @Override
    public void actionAfterLeftToRightSwap(RandomJokes randomJokes) {
        Toast.makeText(this, "Joke saved to Database", Toast.LENGTH_LONG).show();
       JokesApplication.getRoom().jokesDao().insert(randomJokes);
    }


    @Override
    public void actionAfterRightToLeftSwap() {
        presenter.getDataFromApi();


    }

    @Override
    public void showError() {
        jokeProgress.setVisibility(View.INVISIBLE);
        Toast.makeText(this, "There is a problem with internet connection :((", Toast.LENGTH_LONG).show();

    }

    @Override
    public void updateListOfJoke(List<RandomJokes> jokes) {

    }


    @Override
    public void showProgress() {
        jokeProgress.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.buttonOnToolbar)
    public void buttonOnToolbarAction(){
       Intent intent = new Intent(this, DrawerActivity.class);
       startActivity(intent);
    }

    @OnClick(R.id.buttonOnToolbarNotification)
    public void showJokeNotification () {
       // createChannel();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,
                NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_menu_black_24dp)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_message))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
    }
    /*
    private void createChannel(){
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = null;
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel("ChannelId", "Channel", importance);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel.setDescription("Reminders");
        }
// Register the channel with the notifications manager
        NotificationManager mNotificationManager =
                (NotificationManager) getApplication().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotificationManager.createNotificationChannel(channel);
        }
    }
*/

}


