package joke.k.myapplication.login.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;



import joke.k.myapplication.login.data.dao.JokesDao;
import joke.k.myapplication.login.data.RandomJokes;

@Database(entities = {RandomJokes.class},version = 1)
public abstract class JokesDatabase extends RoomDatabase {
   public static final String NAME = "JokeDataBase";
   public abstract JokesDao jokesDao();
}
