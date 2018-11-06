package joke.k.myapplication.login.drawer;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import joke.k.myapplication.login.data.DataManager;

public class DrawerPresenter implements DrawerContract.Presenter {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private DataManager dataManager;
    private DrawerContract.View view;

    public DrawerPresenter(DrawerContract.View view, DataManager dataManager) {
        this.view = view;
        this.dataManager = dataManager;
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
       // String isFirstLogIn = prefsManager.validateFirstLogIn(context,prefsManager.getCurrentUserName());
       String isFirstLogIn = dataManager.validateFirstLogIn(context,dataManager.getCurrentUserNameFromPrefs());
       if(isFirstLogIn.contentEquals("Yes")){

         //  prefsManager.setFirstLogIn("No");
           dataManager.setFirstLogIn("No");

           //prefsManager.changeFirstLogIn(context);
           dataManager.changeFirstLogIn(context);

           view.shouldDialogBeDisplayed();

       }
    }


    private String timeInString(int hour, int minute) {
        String time = String.valueOf(hour) + ":" + String.valueOf(minute);
        return time;
    }





}



