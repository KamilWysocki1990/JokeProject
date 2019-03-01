package joke.k.myapplication.login.drawer.fragments.databaseFragment.showSavedJoke;

import joke.k.myapplication.login.data.DataManager;
import joke.k.myapplication.login.data.dao.JokesDao;
import joke.k.myapplication.login.data.RandomJokes;

public class ShowSavedJokePresenter implements ShowSavedJokeContract.Presenter {

    ShowSavedJokeContract.View view;
    private RandomJokes joke;
    private DataManager dataManager;


    public ShowSavedJokePresenter(ShowSavedJokeContract.View view, DataManager dataManager) {
        this.view = view;
        this.dataManager = dataManager;
    }

    @Override
    public void handleJoke(int jokeId) {

        joke = dataManager.getJokeById(jokeId);
        view.sendJokeFromDatabase(joke);
    }


}
