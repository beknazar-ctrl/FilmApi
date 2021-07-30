package com.example.filmapi.remote;

import com.example.filmapi.data.model.Films;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FilmApi {

    @GET("films")
    Call<List<Films>> getFilms();

    @GET("films/{id}")
    Call<Films> getFilmById(@Path("id") String id
    );
}
