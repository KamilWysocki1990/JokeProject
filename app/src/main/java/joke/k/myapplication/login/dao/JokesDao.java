package joke.k.myapplication.login.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;



import java.util.List;

import joke.k.myapplication.login.data.RandomJokes;

@Dao
public interface JokesDao {

    @Query("SELECT * FROM RandomJokes")
    List<RandomJokes> getAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RandomJokes randomJokes);

    @Delete
    void delete(RandomJokes randomJokes);

    @Query("SELECT * FROM RandomJokes WHERE id LIKE :jokeId LIMIT 1")
    RandomJokes getJokeById(int jokeId);

   @Query("SELECT *FROM RandomJokes WHERE accountName LIKE:account LIMIT 1000")
    List<RandomJokes> getJokeByAccount(String account);
}
