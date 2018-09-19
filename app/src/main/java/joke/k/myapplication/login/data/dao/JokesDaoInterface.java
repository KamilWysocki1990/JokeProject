package joke.k.myapplication.login.data.dao;

import android.arch.persistence.room.Delete;

import java.util.List;

import joke.k.myapplication.login.data.RandomJokes;

public interface JokesDaoInterface {


  //  List<RandomJokes> getAll();
    void insert(RandomJokes randomJokes);
    void delete(RandomJokes randomJokes);
   RandomJokes getJokeById(int jokeId);
  List<RandomJokes> getJokeByAccount(String account);
}
