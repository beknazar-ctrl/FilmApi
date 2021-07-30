package com.example.filmapi.ui.film_detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filmapi.databinding.FragmentFilmDetailBinding;
import com.example.filmapi.data.model.Films;
import com.example.filmapi.remote.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FilmDetailFragment extends Fragment {
    private FragmentFilmDetailBinding binding;

    private TextView infoTitle;
    private TextView infoProducer;
    private TextView infoDescription;
    private String filmId;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            filmId = getArguments().getString("filmId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RetrofitBuilder.getInstance().getFilmById(filmId).enqueue(new Callback<Films>() {
            @Override
            public void onResponse(Call<Films> call, Response<Films> response) {
                if (response.isSuccessful() && response.body() != null) {
                    binding.infoTitle.setText(response.body().getTitle());
                    binding.infoProducer.setText(response.body().getProducer());
                    binding.infoDescription.setText(response.body().getDescription());
                } else {
                    Toast.makeText(requireActivity(), "Error", +response.code());
                }

            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {
                Toast.makeText(requireActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}

