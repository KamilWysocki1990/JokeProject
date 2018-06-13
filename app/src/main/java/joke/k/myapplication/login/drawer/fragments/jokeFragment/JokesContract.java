package com.myapp.k.myapp.jokes;


import android.view.MotionEvent;

import com.myapp.k.myapp.data.RandomJokes;

import java.util.List;

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
