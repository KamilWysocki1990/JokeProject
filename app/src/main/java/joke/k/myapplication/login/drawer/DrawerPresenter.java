package joke.k.myapplication.login.drawer;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import joke.k.myapplication.R;

public class DrawerPresenter implements DrawerContract.Presenter {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private DrawerContract.View view;

    public DrawerPresenter(DrawerContract.View view) {
        this.view = view;
    }

    @Override
    public void customTimeNotification(int hour, int minute) {
        String time = timeInString(hour, minute);
        Observable<String> observable = Observable.just(time);
        compositeDisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        timeToSet -> {
                            // onNext
                            timeToSet = time;
                            view.showCustomTime(time);
                            view.createCustomNotification(hour, minute);
                        },
                        throwable -> {
                            // onError
                        },
                        () -> {
                            // onCompleted
                        })
        );
    }

    private String timeInString(int hour, int minute) {
        String time = String.valueOf(hour) + ":" + String.valueOf(minute);
        return time;
    }





}



