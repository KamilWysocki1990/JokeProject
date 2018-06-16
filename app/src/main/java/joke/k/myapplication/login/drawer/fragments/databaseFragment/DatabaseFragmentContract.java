package com.myapp.k.myapp.fragments.databaseFragment;

import com.myapp.k.myapp.data.RandomJokes;

import java.util.List;
import java.util.Random;

public interface DatabaseFragmentContract {

    interface View{
        void updateList(List<RandomJokes> jokes);
    }
    interface Presenter{
        void getJokesFromRoom();


    }
}
