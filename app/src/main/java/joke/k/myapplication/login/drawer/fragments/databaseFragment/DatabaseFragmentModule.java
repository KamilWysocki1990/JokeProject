package joke.k.myapplication.login.drawer.fragments.databaseFragment;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import joke.k.myapplication.login.dao.JokesDao;
@Module
public class DatabaseFragmentModule {
    DatabaseFragmentContract.View view;

    public DatabaseFragmentModule(DatabaseFragmentContract.View view) {
        this.view = view;
    }


    @Provides
    @Singleton
    DatabaseFragmentContract.Presenter provideDatabaseFragmentPresenter(JokesDao jokesDao){
        return new DatabaseFragmentPresenter(view,jokesDao){

        };
    }
}
