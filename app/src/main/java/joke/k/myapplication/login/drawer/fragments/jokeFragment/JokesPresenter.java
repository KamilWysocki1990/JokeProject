package joke.k.myapplication.login.drawer.fragments.jokeFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import joke.k.myapplication.login.data.DataManager;
import joke.k.myapplication.login.data.RandomJokes;

public class JokesPresenter implements JokesContract.Presenter, LifecycleObserver {
    private JokesContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RandomJokes randomJokes;
    private DataManager dataManager;


    private float x2, x1;


    public JokesPresenter(JokesContract.View view,DataManager dataManager) {
        this.view = view;
        this.dataManager = dataManager;
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

           // compositeDisposable.add(api.getJokes()
                compositeDisposable.add(dataManager.getJokes()
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
      //  randomJokes.setAccountName(prefsManager.getLoginName());
        randomJokes.setAccountName(dataManager.getLoginName());

       // jokesDao.insert(randomJokes);
        dataManager.insert(randomJokes);
    }

    @Override
    public void sendJokeNotification() {
        {

            compositeDisposable.add(dataManager.getJokes()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            randomJokes -> {
                                // onNext
                                this.randomJokes = randomJokes;

                                dataManager.setJokeForNotification(randomJokes.getSetup(),randomJokes.getPunchline());
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

}


