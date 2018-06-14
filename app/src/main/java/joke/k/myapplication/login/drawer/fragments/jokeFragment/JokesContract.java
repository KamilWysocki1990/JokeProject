package joke.k.myapplication.login.drawer.fragments.jokeFragment;


import android.view.MotionEvent;



import java.util.List;

import joke.k.myapplication.login.data.RandomJokes;

public interface JokesContract {

    interface View {
        void showData(RandomJokes randomJokes);
        boolean swapTouch(MotionEvent event);
        void actionAfterLeftToRightSwap(RandomJokes randomJokes);
        void actionAfterRightToLeftSwap();
        void showProgress();
        void showError();
        void updateListOfJoke(List<RandomJokes> jokes);


    }



    interface Presenter{

        void getDataFromApi();
        void onSwapDetected(MotionEvent event);
        void addJokeToDatabase();
        void showJokeNotification();



    }




}
