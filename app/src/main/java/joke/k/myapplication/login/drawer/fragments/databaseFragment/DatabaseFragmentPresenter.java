package joke.k.myapplication.login.drawer.fragments.databaseFragment;


import java.util.ArrayList;
import java.util.List;

import joke.k.myapplication.login.data.DataManager;
import joke.k.myapplication.login.data.dao.JokesDao;
import joke.k.myapplication.login.data.prefs.PrefsManager;
import joke.k.myapplication.login.data.RandomJokes;

public class DatabaseFragmentPresenter implements DatabaseFragmentContract.Presenter {

    private DatabaseFragmentContract.View view;
    private DataManager dataManager;
    private List<RandomJokes> jokes = new ArrayList<>();

    public DatabaseFragmentPresenter(DatabaseFragmentContract.View view, DataManager dataManager) {
        this.view = view;
        this.dataManager = dataManager;

    }


    @Override
    public void getJokesFromRoom() {
        view.updateList(dataManager.getJokeByAccount(dataManager.getCurrentUserNamee()));
    }

    @Override
    public void deleteJokeFromDatabase(int id) {
        dataManager.delete(dataManager.getJokeById(id));
    }

}
