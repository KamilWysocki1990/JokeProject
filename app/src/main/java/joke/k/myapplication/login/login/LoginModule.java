package joke.k.myapplication.login.login;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import joke.k.myapplication.login.data.AppDataManager;
import joke.k.myapplication.login.data.DataManager;
import joke.k.myapplication.login.data.prefs.PrefsManager;

@Module
public class LoginModule {

    LoginContract.View view ;

    public LoginModule(LoginContract.View view) {
        this.view = view;
    }



   @Provides
   @Singleton
    LoginContract.Presenter provideLoginPresenter(PrefsManager prefsManager, DataManager DataManager){
        return new LoginPresenter(view,prefsManager,DataManager);
   }

}
