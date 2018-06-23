package joke.k.myapplication.login.drawer.fragments.jokeFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;
import io.reactivex.disposables.CompositeDisposable;
import joke.k.myapplication.R;
import joke.k.myapplication.login.JokeApplication;
import joke.k.myapplication.login.api.Api;
import joke.k.myapplication.login.data.RandomJokes;



public class JokesFragment extends Fragment implements JokesContract.View {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.askJokes)
    TextView textForAskJokes;

    @BindView(R.id.anserwJokes)
    TextView textForAnserwJokes;

    @BindView(R.id.joke_progress)
    ProgressBar jokeProgress;



    private Api api;

    private List<RandomJokes> jokes = new ArrayList<>();


        @Inject
        JokesContract.Presenter presenter;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_jokes, container, false);
            ButterKnife.bind(this, view);
            ((JokeApplication) getActivity().getApplication()).getAppComponent()
                    .plus(new JokesModule(this))
                    .inject(this);



 return view;
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
        Toast.makeText(getContext(), "Joke saved to Database", Toast.LENGTH_LONG).show();

       presenter.addJokeToDatabase();
    }


    @Override
    public void actionAfterRightToLeftSwap() {
        presenter.getDataFromApi();


    }

    @Override
    public void showError() {
        jokeProgress.setVisibility(View.INVISIBLE);
        Toast.makeText(getContext(), "There is a problem with internet connection :((", Toast.LENGTH_LONG).show();

    }

    @Override
    public void updateListOfJoke(List<RandomJokes> jokes) {

    }


    @Override
    public void showProgress() {
        jokeProgress.setVisibility(View.VISIBLE);
    }


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




