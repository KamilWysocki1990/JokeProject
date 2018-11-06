package joke.k.myapplication.login.drawer.fragments.jokeFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import joke.k.myapplication.R;
import joke.k.myapplication.login.JokeApplication;
import joke.k.myapplication.login.data.RandomJokes;
import joke.k.myapplication.login.drawer.AlarmScheduler;
import joke.k.myapplication.login.drawer.DrawerActivity;
import joke.k.myapplication.login.drawer.fragments.TimePickerFragment;


public class JokesFragment extends Fragment implements JokesContract.View {

    CommunicationWithDrawerActivity communicationWithDrawerActivity;


    public interface CommunicationWithDrawerActivity {
        void sendActualJokeToShare(String askJoke, String askBody);


    }

    public static final String TIME_PICKER_TAG = "timePickerTag";
    private String notificationAskJoke = "askJokeToSend";
    private String notificationBodyJoke = "bodyJokeAsk";

    @BindView(R.id.askJokes)
    TextView textForAskJokes;

    @BindView(R.id.anserwJokes)
    TextView textForAnserwJokes;

    @BindView(R.id.joke_progress)
    ProgressBar jokeProgress;

    @BindView(R.id.switchButtonNotificationOn)
    ToggleButton toggleNotificationButtonOn;

    @BindView(R.id.switchButtonNotificationOff)
    ToggleButton toggleNotificationButtonOff;

    @Inject
    JokesContract.Presenter presenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jokes, container, false);
        ButterKnife.bind(this, view);
        ((JokeApplication) getActivity().getApplication()).getAppComponent()
                .plus(new JokesModule(this))
                .inject(this);

        toggleNotificationButtonOff.setChecked(true);
        boolean isClicked = getArguments().getBoolean("CancelClicked");

        shouldButtonNotificationBeTurnedOff(isClicked);

        if(checkActiveAlarm()){
            toggleNotificationButtonOn.setChecked(true);
            toggleNotificationButtonOff.setChecked(false);
        }


        return view;


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof JokesFragment.CommunicationWithDrawerActivity) {
            communicationWithDrawerActivity = (CommunicationWithDrawerActivity) context;

        } else {
            throw new ClassCastException(context.toString() + getString(R.string.class_cast_exception_in_joke_fragment));
        }
    }

    @Override
    public void onDetach() {
        communicationWithDrawerActivity = null;
        super.onDetach();


    }

    private void shouldButtonNotificationBeTurnedOff(boolean isClicked) {
        if (isClicked) {
            actionButtonOff();
        }
    }


    @Override
    public void showData(RandomJokes randomJokes) {
        textForAskJokes.setText(randomJokes.getSetup());
        textForAnserwJokes.setText(randomJokes.getPunchline());
        communicationWithDrawerActivity.sendActualJokeToShare(randomJokes.getSetup(),randomJokes.getPunchline());
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
        Toast.makeText(getContext(), R.string.ErrorText, Toast.LENGTH_LONG).show();

    }

    @Override
    public void updateListOfJoke(List<RandomJokes> jokes) {

    }

    @OnClick(R.id.switchButtonNotificationOn)
    public void notificationButtonOn() {
        presenter.sendJokeNotification();
        toggleNotificationButtonOn.setChecked(true);
        if (toggleNotificationButtonOn.isChecked()) {
            toggleNotificationButtonOff.setChecked(false);

        }

        android.support.v4.app.DialogFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(getActivity().getSupportFragmentManager(), "Time Picker");
    }

    @Override
    public void passTimeFromTimePicker(int hour, int minute) {
        presenter.providingTimeFromTimePicker(hour, minute);
    }

    @Override
    public void passJoke(String askJoke, String bodyJoke) {
        notificationAskJoke = askJoke;
        notificationBodyJoke = bodyJoke;

    }

    @OnClick(R.id.switchButtonNotificationOff)
    public void notificationButtonOff() {
        actionButtonOff();
        Toast.makeText(getContext(), "Notification turned off", Toast.LENGTH_LONG).show();
        cancelAlarm();
    }

    private void actionButtonOff() {
        toggleNotificationButtonOff.setChecked(true);
        if (toggleNotificationButtonOff.isChecked()) {
            toggleNotificationButtonOn.setChecked(false);
        }
    }

    @Override
    public void showTestToast() {
        Toast.makeText(getContext(), "Working Right !", Toast.LENGTH_LONG).show();
    }


    @Override
    public void showProgress() {
        jokeProgress.setVisibility(View.VISIBLE);
    }

    public void cancelAlarm(){
        AlarmScheduler.cancelAlarm(getContext());
    }

    public boolean checkActiveAlarm(){
        boolean isActive = AlarmScheduler.checkAlarm(getContext());
    return isActive;
    }


}












