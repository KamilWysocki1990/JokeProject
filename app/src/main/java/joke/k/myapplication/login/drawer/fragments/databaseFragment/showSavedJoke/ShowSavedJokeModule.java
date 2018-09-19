package joke.k.myapplication.login.drawer.fragments.databaseFragment.showSavedJoke;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import joke.k.myapplication.login.data.DataManager;
import joke.k.myapplication.login.data.dao.JokesDao;

@Module
public class ShowSavedJokeModule {

    ShowSavedJokeContract.View view;

    public ShowSavedJokeModule(ShowSavedJokeContract.View view) {
        this.view = view;
    }


    @Provides
    @Singleton
    ShowSavedJokeContract.Presenter provideDatabaseFragmentPresenter( DataManager dataManager){
        return new ShowSavedJokePresenter(view,dataManager){

        };
    }
}



