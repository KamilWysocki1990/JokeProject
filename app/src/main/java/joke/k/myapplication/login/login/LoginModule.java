package joke.k.myapplication.login.login;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import joke.k.myapplication.login.data.PrefsManager;

@Module
public class LoginModule {

    LoginContract.View view ;

    public LoginModule(LoginContract.View view) {
        this.view = view;
    }



   @Provides
   @Singleton
    LoginContract.Presenter provideLoginPresenter(PrefsManager prefsManager){
        return new LoginPresenter( view,prefsManager);
   }

}
