package joke.k.myapplication.login.drawer.fragments.databaseFragment;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import joke.k.myapplication.login.data.DataManager;
import joke.k.myapplication.login.data.dao.JokesDao;
import joke.k.myapplication.login.data.prefs.PrefsManager;

@Module
public class DatabaseFragmentModule {
    DatabaseFragmentContract.View view;

    public DatabaseFragmentModule(DatabaseFragmentContract.View view) {
        this.view = view;
    }


    @Provides
    @Singleton
    DatabaseFragmentContract.Presenter provideDatabaseFragmentPresenter(DataManager dataManager){
        return new DatabaseFragmentPresenter(view,dataManager){

        };
    }
}
