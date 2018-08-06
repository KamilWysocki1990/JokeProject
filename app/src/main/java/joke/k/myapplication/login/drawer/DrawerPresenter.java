package joke.k.myapplication.login.drawer;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DrawerPresenter implements DrawerContract.Presenter {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private DrawerContract.View view;

    public DrawerPresenter(DrawerContract.View view) {
        this.view = view;
    }

    @Override
    public void customTimeNotification(int hour, int minute) {
      String time = timeInString(hour,minute);
        Observable<String> observable = Observable.just(time);
        compositeDisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        timeToSet -> {
                            // onNext
                            timeToSet = time;
                            view.showCustomTime(time);
                        },
                        throwable -> {
                            // onError
                        },
                        () -> {
                            // onCompleted
                        })
        );
    }

        private String timeInString(int hour, int minute){
            String time = String.valueOf(hour)+":"+String.valueOf(minute);
            return time;
        }


}
