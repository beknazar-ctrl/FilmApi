package com.example.filmapi.frameworks;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.filmapi.data.model.Films;

@Database(entities = {Films.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract FilmDao filmDao();


}
