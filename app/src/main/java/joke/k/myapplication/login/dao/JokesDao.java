package com.myapp.k.myapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.myapp.k.myapp.data.RandomJokes;

import java.util.List;

@Dao
public interface JokesDao {

    @Query("SELECT * FROM RandomJokes")
    List<RandomJokes> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RandomJokes randomJokes);
}
