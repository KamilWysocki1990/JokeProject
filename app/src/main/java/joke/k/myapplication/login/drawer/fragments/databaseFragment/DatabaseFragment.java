package com.myapp.k.myapp.fragments.databaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapp.k.myapp.JokesApplication;
import com.myapp.k.myapp.R;
import com.myapp.k.myapp.data.RandomJokes;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DatabaseFragment extends Fragment implements DatabaseFragmentContract.View  {

    @BindView(R.id.jokes_recycler)
    RecyclerView jokesRecycler;

    private DatabaseFragmentContract.Presenter presenter;
    private DatabaseFragmentAdapter databaseFragmentAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this, view);

        presenter = new DatabaseFragmentPresenter(this, JokesApplication.getRoom().jokesDao());

        setupRecycler();
        presenter.getJokesFromRoom();
        return view;



    }

    @Override
    public void updateList(List<RandomJokes> jokes) {
       databaseFragmentAdapter.updateJokesList(jokes);
    }

    private void setupRecycler(){
        jokesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        databaseFragmentAdapter = new DatabaseFragmentAdapter();
        jokesRecycler.setAdapter(databaseFragmentAdapter);

        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        jokesRecycler.addItemDecoration(decoration);
    }
}

