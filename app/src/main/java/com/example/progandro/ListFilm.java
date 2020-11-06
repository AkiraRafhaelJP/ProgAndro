package com.example.progandro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListFilm extends AppCompatActivity{

    RecyclerView recyclerView;
    String judul[];
    int images[] = {R.drawable.exmachina, R.drawable.gameofthrones,
            R.drawable.harrypottersorcerer, R.drawable.iamrobot,
            R.drawable.imitationgame, R.drawable.insidious,
            R.drawable.lordoftherings, R. drawable.piratesstranger,
            R.drawable.sherlockbbc, R.drawable.threeidiots};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_film);
        //Toast.makeText(getApplicationContext(), "Ini ListFilm", Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.recyclerView);
        judul = getResources().getStringArray(R.array.judul_film);

        MyAdapter myAdapter = new MyAdapter(this, judul, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void backToHome(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
