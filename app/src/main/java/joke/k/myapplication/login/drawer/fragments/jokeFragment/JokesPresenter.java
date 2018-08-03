package joke.k.myapplication.login.drawer.fragments.jokeFragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.MotionEvent;
import android.widget.CompoundButton;
import android.widget.TimePicker;


import java.util.Calendar;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import joke.k.myapplication.login.api.Api;
import joke.k.myapplication.login.dao.JokesDao;
import joke.k.myapplication.login.data.RandomJokes;

public class JokesPresenter implements JokesContract.Presenter, LifecycleObserver, JokesContract.TimeToNotification {
    private JokesContract.View view;
    private Api api;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RandomJokes randomJokes;
    private JokesDao jokesDao;
    private float x2, x1;
  //  private int hour,minute;

    public JokesPresenter(JokesContract.View view, Api api, JokesDao jokesDao) {
        this.view = view;
        this.api = api;
        this.jokesDao = jokesDao;

        ((LifecycleOwner) this.view).getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void onCreate() {
        getDataFromApi();

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy() {
        compositeDisposable.clear();
    }

    @Override
    public void getDataFromApi() {
        {
            view.showProgress();
            compositeDisposable.add(api.getJokes()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            randomJokes -> {
                                // onNext
                                this.randomJokes = randomJokes;

                                view.showData(randomJokes);
                            },
                            throwable -> {
                                // onError
                                view.showError();
                            },
                            () -> {
                                // onCompleted
                            })
            );

        }
    }


    @Override
    public void onSwapDetected(MotionEvent event) {
        switch (event.getAction()) {
            //coordinates from  1st touch
            case MotionEvent.ACTION_DOWN: {
                x1 = event.getX();

                break;
            }
            case MotionEvent.ACTION_UP: {
                x2 = event.getX();

                // left to right swap
                if (x1 < x2) {
                    view.actionAfterLeftToRightSwap(randomJokes);

                }

                // right to left swap
                if (x1 > x2) {
                    view.actionAfterRightToLeftSwap();

                    break;


                }

            }

        }
    }

    @Override
    public void addJokeToDatabase() {
        jokesDao.insert(randomJokes);
    }

    @Override
    public void showJokeNotification() {



    }

    @Override
    public void notificationButtonSetupOn() {
    //  view.notificationButtonOn();

    }

    @Override
    public void notificationButtonSetupOff() {
     //   view.notificationButtonOff();
    }



    @Override
    public void providingTimeFromTimePicker(int hourOfDay, int minute) {
        String time = String.valueOf(hourOfDay)+":"+String.valueOf(minute);
        Observable<String> observable = Observable.just(time);
        compositeDisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        timeToSet -> {
                            // onNext
                            timeToSet = time;
                            view.showTestToast();
                        },
                        throwable -> {
                            // onError
                        },
                        () -> {
                            // onCompleted
                        })
        );


    }
/*
    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{



        public  TimePickerFragment(){}

        int hour,minutes;

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
            hour = hourOfDay;
            minutes = minute;
        }

    }

*/

}


