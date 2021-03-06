package joke.k.myapplication.login.drawer.fragments.databaseFragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import joke.k.myapplication.R;
import joke.k.myapplication.login.data.dao.JokesDao;
import joke.k.myapplication.login.data.RandomJokes;
import joke.k.myapplication.login.drawer.fragments.databaseFragment.showSavedJoke.ShowSavedJokeActivity;

public class DatabaseFragmentAdapter extends RecyclerView.Adapter<DatabaseFragmentAdapter.JokeHolder> {


    DatabaseFragmentContract.JokeDeleteListener jokeDeleteListener;


    private List<RandomJokes> jokesList = new ArrayList<>();

    public DatabaseFragmentAdapter(DatabaseFragmentContract.JokeDeleteListener jokeDeleteListener){
        this.jokeDeleteListener = jokeDeleteListener;
    }

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
        holder.setupJoke(jokesList.get(position),jokeDeleteListener);
    }

    @Override
    public int getItemCount() {
        return jokesList.size();
    }

    class JokeHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_joke_id)
        TextView jokeId;

        @BindView(R.id.item_joke_remove_icon)
        ImageView removeJokeIcon;

    public  JokeHolder (View jokeHolderView ){
        super(jokeHolderView);
        ButterKnife.bind(this,jokeHolderView);


        itemView.setOnClickListener(view ->{
            Intent intentJoke = new Intent(itemView.getContext(), ShowSavedJokeActivity.class);
            intentJoke.putExtra(ShowSavedJokeActivity.JOKE_ID_KEY,jokesList.get(getAdapterPosition()).getId());
            itemView.getContext().startActivity(intentJoke);
        });

        removeJokeIcon.setOnClickListener(view  ->{
                jokeDeleteListener.removeJokeFromDatabase(jokesList.get(getAdapterPosition()).getId());
                jokesList.remove(jokesList.get(getAdapterPosition()));

                notifyItemRemoved(getAdapterPosition());

        });





    }
        public void setupJoke(RandomJokes randomJokes,DatabaseFragmentContract.JokeDeleteListener jokeDeleteListener ) {
            jokeId.setText(randomJokes.getSetup());

        }
    }



}
