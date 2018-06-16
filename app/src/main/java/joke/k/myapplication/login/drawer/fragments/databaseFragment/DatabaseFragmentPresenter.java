package com.myapp.k.myapp.fragments.databaseFragment;

import com.myapp.k.myapp.dao.JokesDao;
import com.myapp.k.myapp.data.RandomJokes;

import java.util.ArrayList;
import java.util.List;

public class DatabaseFragmentPresenter implements DatabaseFragmentContract.Presenter {

    private DatabaseFragmentContract.View view;

    public DatabaseFragmentPresenter(DatabaseFragmentContract.View view, JokesDao jokesDao) {
        this.view = view;
        this.jokesDao = jokesDao;
    }

    private JokesDao jokesDao;
    private List<RandomJokes> jokes = new ArrayList<>();


    @Override
    public void getJokesFromRoom() {
        view.updateList(jokesDao.getAll());
    }




}
