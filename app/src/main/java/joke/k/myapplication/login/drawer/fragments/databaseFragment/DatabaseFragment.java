package joke.k.myapplication.login.drawer.fragments.databaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import joke.k.myapplication.R;
import joke.k.myapplication.login.JokeApplication;
import joke.k.myapplication.login.data.RandomJokes;


public class DatabaseFragment extends Fragment implements DatabaseFragmentContract.View, DatabaseFragmentContract.JokeDeleteListener  {

    @BindView(R.id.jokes_recycler)
    RecyclerView jokesRecycler;

    private DatabaseFragmentAdapter databaseFragmentAdapter;
    @Inject
    DatabaseFragmentContract.Presenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_database, container, false);
        ButterKnife.bind(this, view);
        ((JokeApplication) getActivity().getApplication()).getAppComponent()
              .plus(new DatabaseFragmentModule(this))
               .inject(this);

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
        databaseFragmentAdapter = new DatabaseFragmentAdapter(this);
        jokesRecycler.setAdapter(databaseFragmentAdapter);

        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        jokesRecycler.addItemDecoration(decoration);

    }

    @Override
    public void removeJokeFromDatabase(int id) {
        presenter.deleteJokeFromDatabase(id);
    }
}

