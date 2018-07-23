package joke.k.myapplication.login.drawer.fragments.jokeFragment;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import io.reactivex.disposables.CompositeDisposable;
import joke.k.myapplication.R;
import joke.k.myapplication.login.JokeApplication;
import joke.k.myapplication.login.api.Api;
import joke.k.myapplication.login.data.RandomJokes;



public class JokesFragment extends Fragment implements JokesContract.View {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public static final String TIME_PICKER_TAG="timePickerTag";

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

    @OnClick(R.id.switchButtonNotificationOn)
    public void notificationButtonOn() {
        toggleNotificationButtonOn.setChecked(true);
        if (toggleNotificationButtonOn.isChecked()) {
            toggleNotificationButtonOff.setChecked(false);

        }

        android.support.v4.app.DialogFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(getActivity().getSupportFragmentManager(),"Time Picker");


    }

    @OnClick(R.id.switchButtonNotificationOff)
    public void notificationButtonOff() {
        toggleNotificationButtonOff.setChecked(true);
        if (toggleNotificationButtonOff.isChecked()) {
            toggleNotificationButtonOn.setChecked(false);

        }



    }


    @Override
    public void showProgress() {
        jokeProgress.setVisibility(View.VISIBLE);
    }




    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        public TimePickerFragment(){}

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker

            final Calendar c = Calendar.getInstance();
           int  hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);


            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(),android.R.style.Theme_Holo_Dialog, this, hour, minute, true );
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            final Calendar c = Calendar.getInstance();
            hourOfDay = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);


        }
    }




    }






