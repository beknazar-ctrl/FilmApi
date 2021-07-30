package com.example.filmapi.ui.film_list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.filmapi.R;
import com.example.filmapi.data.model.Films;
import com.example.filmapi.remote.RetrofitBuilder;
import com.example.filmapi.ui.App;
import com.example.filmapi.ui.film_list.adapter.FilmAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FilmListFragment extends Fragment implements FilmAdapter.CallBack, FilmAdapter.SaveRoom {

    private final FilmAdapter adapter = new FilmAdapter(this,this);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_film_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initView(view);
        getFilmsFromRetrofit();
    }

    private void getFilmsFromRetrofit() {
        RetrofitBuilder.getInstance().getFilms().enqueue(new Callback<List<Films>>() {
            @Override
            public void onResponse(Call<List<Films>> call, Response<List<Films>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.addItems(response.body());
                    Log.e("tag", "success" + response.body());
                } else {
                    Log.e("tag", "filed" + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Films>> call, Throwable t) {
                Log.e("tag", "failure" + t.getLocalizedMessage());
            }
        });

    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void filmClick(Films films) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.filmListFragment);
        Bundle bundle =new Bundle();
        bundle.putString("filmId",films.getId());
        Log.e("tag","success"+films.getId());
        navController.navigate(R.id.filmDetailFragment,bundle);
    }

    @Override
    public void Click(Films films) {
        App.getAppDataBase().filmDao().insert(new Films(films.getTitle(),films.getProducer()));

    }
}