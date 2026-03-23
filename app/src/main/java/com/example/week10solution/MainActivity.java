package com.example.week10solution;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView movieRecyclerView;
    private MovieAdapter adapter;
    private List<Movie> movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //loading data:
        setupRecyclerView();
        loadMovieData();
    }

    public void setupRecyclerView(){
        movies = new ArrayList<>();
        movieRecyclerView = findViewById(R.id.movieRecyclerView);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MovieAdapter(movies);
        movieRecyclerView.setAdapter(adapter);
    }

    public void loadMovieData(){
        try {
            List<Movie> loadedMovies = JsonUtils.loadMoviesFromJson(this, R.raw.movies);
            if (loadedMovies != null) adapter.updateMovies(loadedMovies);
        }
        catch (Exception e) {
            Log.e("MainActivity", "An error occurred: ", e);
            showError("An error occurred while loading movies.");
        }
    }

    public void showError(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}