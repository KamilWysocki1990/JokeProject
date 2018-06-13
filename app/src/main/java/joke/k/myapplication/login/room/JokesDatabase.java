package com.myapp.k.myapp.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.myapp.k.myapp.dao.JokesDao;
import com.myapp.k.myapp.data.RandomJokes;

@Database(entities = {RandomJokes.class},version = 1)
public abstract class JokesDatabase extends RoomDatabase {
   public static final String NAME = "JokeDataBase";
   public abstract JokesDao jokesDao();
}
