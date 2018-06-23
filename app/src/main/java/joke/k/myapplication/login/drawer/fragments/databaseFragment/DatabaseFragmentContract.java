package joke.k.myapplication.login.drawer.fragments.databaseFragment;


import java.util.List;

import joke.k.myapplication.login.data.RandomJokes;

public interface DatabaseFragmentContract {

    interface View{
        void updateList(List<RandomJokes> jokes);
    }
    interface Presenter{
        void getJokesFromRoom();
        void deleteJokeFromDatabase(int id);

    }
    interface JokeDeleteListener{
        void removeJokeFromDatabase(int id);

    }
}
