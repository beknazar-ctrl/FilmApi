package com.example.filmapi.frameworks;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.filmapi.data.model.Films;

import java.util.List;

@Dao
public interface FilmDao {

    @Query("SELECT * FROM Films")
    LiveData<List<Films>> getAllFilms();

    @Insert
    void insert(Films films);

}
