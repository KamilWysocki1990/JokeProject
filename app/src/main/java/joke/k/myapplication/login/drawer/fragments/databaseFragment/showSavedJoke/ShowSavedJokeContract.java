package joke.k.myapplication.login.drawer.fragments.databaseFragment.showSavedJoke;

import joke.k.myapplication.login.data.RandomJokes;

public interface ShowSavedJokeContract {

 interface View{

     void sendJokeFromDatabase(RandomJokes jokeById);

    }

    interface Presenter{

        void handleJoke(int jokeId);
    }

}
