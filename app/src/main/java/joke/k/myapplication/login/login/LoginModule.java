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
    LoginContract.Presenter provideLoginPresenter(DataManager dataManager){
        return new LoginPresenter(view,dataManager);
   }

}
