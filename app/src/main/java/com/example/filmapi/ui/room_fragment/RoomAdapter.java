package com.example.filmapi.ui.room_fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmapi.R;
import com.example.filmapi.data.model.Films;
import com.example.filmapi.databinding.ListRoomBinding;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.VHR> {

    private ListRoomBinding listRoom;
    private final List<Films> list = new ArrayList<>();

    @NonNull
    @Override
    public VHR onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        listRoom = ListRoomBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new VHR(listRoom);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomAdapter.VHR holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(List<Films> films) {
        list.addAll(films);
        notifyDataSetChanged();

    }

    public static class VHR extends RecyclerView.ViewHolder {
        private final ListRoomBinding listBinding;

        public VHR(ListRoomBinding listBinding) {
            super(listBinding.getRoot());
            this.listBinding = listBinding;
        }

        public void onBind(Films films) {
            listBinding.roomTitle.setText(films.getTitle());
            listBinding.roomProducer.setText(films.getProducer());
        }
    }

    public interface clickRoom {
        void click();
    }
}
