package com.example.progandro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    String data[];
    int img[];

    public MyAdapter(Context context, String judul[], int cover_film[]){
        data = judul;
        img = cover_film;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_film, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.judulFilm.setText(data[position]);
        holder.cover.setImageResource(img[position]);
    }

    @Override
    public int getItemCount() {
        return img.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView judulFilm;
        ImageView cover;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            judulFilm = itemView.findViewById(R.id.judul_txt);
            cover = itemView.findViewById(R.id.cover_film);
        }
    }
}
