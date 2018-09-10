package joke.k.myapplication.login.drawer.fragments.jokeFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import joke.k.myapplication.login.api.Api;
import joke.k.myapplication.login.dao.JokesDao;
import joke.k.myapplication.login.data.PrefsManager;
import joke.k.myapplication.login.login.LoginContract;
import joke.k.myapplication.login.login.LoginPresenter;

@Module
public class JokesModule {

    JokesContract.View view;

    public JokesModule(JokesContract.View view) {
        this.view = view;
    }





    @Provides
    @Singleton
    JokesContract.Presenter provideJokesPresenter(Api api, JokesDao jokesDao, PrefsManager prefsManager){
        return new JokesPresenter(view,api,jokesDao, prefsManager) {
        };
    }


}
