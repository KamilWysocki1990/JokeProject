package joke.k.myapplication.login.drawer.fragments.databaseFragment.showSavedJoke;

import joke.k.myapplication.login.data.RandomJokes;

public interface ShowSavedJokeContract {

 public interface View{

     void sendJokeFromDatabase(RandomJokes jokeById);
    }

    public interface Presenter{

        void handleJoke(int jokeId);
    }

}
