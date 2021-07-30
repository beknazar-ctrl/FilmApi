package com.example.filmapi.ui.film_list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmapi.R;
import com.example.filmapi.data.model.Films;

import java.util.ArrayList;
import java.util.List;


public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

    private List<Films> list = new ArrayList<>();
    private CallBack callBack;
    private SaveRoom saveRoom;

    public FilmAdapter(CallBack callBack,SaveRoom saveRoom) {
        this.callBack = callBack;
        this.saveRoom = saveRoom;
    }

    public FilmAdapter() {
    }

    @NonNull
    @Override
    public FilmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_list, parent,false);
        return new ViewHolder(view,callBack,saveRoom);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(List<Films> films){
        list.addAll(films);
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView producer;
        private ImageView imageView;
        private CallBack callBack;
        private SaveRoom saveRoom;

        public ViewHolder(@NonNull View itemView, CallBack callBack,SaveRoom saveRoom) {
            super(itemView);
            this.callBack = callBack;
            this.saveRoom = saveRoom;
            title = itemView.findViewById(R.id.text_title_film);
            producer = itemView.findViewById(R.id.text_director_film);
            imageView = itemView.findViewById(R.id.image_save_to_room);

        }

        public void onBind(Films films) {
            title.setText(films.getTitle());
            producer.setText(films.getProducer());

            title.setOnClickListener(v -> callBack.filmClick(films));
            imageView.setOnClickListener(v -> saveRoom.Click(films));

        }
    }

    public interface CallBack {
        void filmClick(Films films);

    }

    public interface SaveRoom {
        void Click(Films films);
    }
}
