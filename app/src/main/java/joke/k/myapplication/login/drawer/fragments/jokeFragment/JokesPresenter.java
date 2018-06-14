package joke.k.myapplication.login.drawer.fragments.jokeFragment;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.view.MotionEvent;



import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import joke.k.myapplication.login.api.Api;
import joke.k.myapplication.login.dao.JokesDao;
import joke.k.myapplication.login.data.RandomJokes;

public class JokesPresenter implements JokesContract.Presenter, LifecycleObserver {
    private JokesContract.View view;
    private Api api;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RandomJokes randomJokes;
    private JokesDao jokesDao;
    float x2, x1;

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
        jokesDao.insert(new RandomJokes());
    }

    @Override
    public void showJokeNotification() {



    }
}
