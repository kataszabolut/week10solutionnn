package com.example.week10solution;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    // attributes:
    private List<Movie> movies;

    // constructor:
    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    // methods:
    @NonNull @Override public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View movieView = inflater.inflate(R.layout.movie_card_item, viewGroup, false);
        return new MovieViewHolder(movieView);
    }
    @Override public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        Movie currentMovie = movies.get(i);
        movieViewHolder.bind(currentMovie);
    }
    @Override public int getItemCount() {
        if (movies != null) return movies.size();
        else return 0;
    }
    public void updateMovies(List<Movie> list) {
        this.movies = list;
        notifyDataSetChanged();
    }
}