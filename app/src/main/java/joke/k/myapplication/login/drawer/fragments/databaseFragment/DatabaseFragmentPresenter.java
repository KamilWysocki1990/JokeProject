package joke.k.myapplication.login.drawer.fragments.databaseFragment;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import joke.k.myapplication.login.dao.JokesDao;
import joke.k.myapplication.login.data.PrefsManager;
import joke.k.myapplication.login.data.RandomJokes;

public class DatabaseFragmentPresenter implements DatabaseFragmentContract.Presenter {

    private DatabaseFragmentContract.View view;
    private PrefsManager prefsManager;


    public DatabaseFragmentPresenter(DatabaseFragmentContract.View view, JokesDao jokesDao, PrefsManager prefsManager) {
        this.view = view;
        this.jokesDao = jokesDao;
        this.prefsManager = prefsManager;
    }


    private JokesDao jokesDao;
    private List<RandomJokes> jokes = new ArrayList<>();


    @Override
    public void getJokesFromRoom() {
        view.updateList(jokesDao.getJokeByAccount(prefsManager.getCurrentUserName()));
    }

    @Override
    public void deleteJokeFromDatabase(int id) {
        jokesDao.delete(jokesDao.getJokeById(id));
    }


}
