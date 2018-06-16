package com.myapp.k.myapp.fragments.databaseFragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapp.k.myapp.R;
import com.myapp.k.myapp.data.RandomJokes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DatabaseFragmentAdapter extends RecyclerView.Adapter<DatabaseFragmentAdapter.JokeHolder> {

    private List<RandomJokes> jokesList = new ArrayList<>();

    public void updateJokesList(List<RandomJokes> jokes){
        jokesList.clear();
        jokesList.addAll(jokes);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public JokeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View jokeHolderView = layoutInflater.inflate(R.layout.item_joke,parent,false);

        return new JokeHolder(jokeHolderView);
    }



    @Override
    public void onBindViewHolder(@NonNull JokeHolder holder, int position) {
        holder.setupJoke(jokesList.get(position));
    }

    @Override
    public int getItemCount() {
        return jokesList.size();
    }

    class JokeHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_joke_id)
        TextView jokeId;

    public  JokeHolder (View jokeHolderView ){
        super(jokeHolderView);
        ButterKnife.bind(this,jokeHolderView);
    }
        public void setupJoke(RandomJokes randomJokes) {
            jokeId.setText("1");
        }
    }



}
