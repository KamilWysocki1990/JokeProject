package joke.k.myapplication.login.drawer.fragments.databaseFragment.showSavedJoke;

import joke.k.myapplication.login.dao.JokesDao;
import joke.k.myapplication.login.data.RandomJokes;

public class ShowSavedJokePresenter implements ShowSavedJokeContract.Presenter {

    ShowSavedJokeContract.View view;
    JokesDao jokesDao;
    private RandomJokes joke;


    public ShowSavedJokePresenter(ShowSavedJokeContract.View view, JokesDao jokesDao) {
        this.view = view;
        this.jokesDao = jokesDao;
    }
    @Override
    public void handleJoke(int jokeId){
    joke = jokesDao.getJokeById(jokeId);
    view.sendJokeFromDatabase(joke);
    }



}
