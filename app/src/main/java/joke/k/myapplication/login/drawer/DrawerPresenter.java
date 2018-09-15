package joke.k.myapplication.login.drawer;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import joke.k.myapplication.login.data.prefs.PrefsManager;

public class DrawerPresenter implements DrawerContract.Presenter {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private PrefsManager prefsManager;
    private DrawerContract.View view;

    public DrawerPresenter(DrawerContract.View view, PrefsManager prefsManager) {
        this.view = view;
        this.prefsManager = prefsManager;
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

    @Override
    public void validateFirstLogIn(Context context) {
       String isFirstLogIn = prefsManager.validateFirstLogIn(context,prefsManager.getCurrentUserName());
       if(isFirstLogIn.contentEquals("Yes")){
           prefsManager.setFirstLogIn("No");
           prefsManager.changeFirstLogIn(context);
           view.shouldDialogBeDisplayed();

       }
    }


    private String timeInString(int hour, int minute) {
        String time = String.valueOf(hour) + ":" + String.valueOf(minute);
        return time;
    }





}



