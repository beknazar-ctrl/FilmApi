package com.example.filmapi.ui.room_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.filmapi.R;
import com.example.filmapi.data.model.Films;
import com.example.filmapi.databinding.FragmentFilmDetailBinding;
import com.example.filmapi.databinding.FragmentRoomBinding;
import com.example.filmapi.ui.App;

import java.util.List;


public class RoomFragment extends Fragment {

    private FragmentRoomBinding bindingRoom;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bindingRoom = FragmentRoomBinding.inflate(inflater, container, false);
        return bindingRoom.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        roomList();
    }

    private void roomList() {
        App.getAppDataBase().filmDao().getAllFilms().observe(getViewLifecycleOwner(), new Observer<List<Films>>() {
            @Override
            public void onChanged(List<Films> films) {
                initRecyclerView(films);
            }
        });
    }

    private void initRecyclerView(List<Films> films) {
        RoomAdapter adapter=new RoomAdapter();
        adapter.addItems(films);
        bindingRoom.recVRoom.setAdapter(adapter);
    }
}