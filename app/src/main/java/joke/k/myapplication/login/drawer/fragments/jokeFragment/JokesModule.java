package joke.k.myapplication.login.drawer.fragments.jokeFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import joke.k.myapplication.login.data.network.Api;
import joke.k.myapplication.login.data.dao.JokesDao;
import joke.k.myapplication.login.data.prefs.PrefsManager;

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
