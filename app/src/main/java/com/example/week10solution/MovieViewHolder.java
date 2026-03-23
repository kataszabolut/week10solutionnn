package com.example.week10solution;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MovieViewHolder extends RecyclerView.ViewHolder {

    private ImageView posterImageView;
    private TextView titleTextView;
    private TextView yearTextView;
    private TextView genreTextView;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        posterImageView = itemView.findViewById(R.id.posterImageView);
        titleTextView = itemView.findViewById(R.id.titleTextView);
        yearTextView = itemView.findViewById(R.id.genreTextView);
        genreTextView = itemView.findViewById(R.id.genreTextView);
    }

    public void bind(Movie movie) {

        //setting title:
        titleTextView.setText(movie.getTitle());

        //setting year:
        yearTextView.setText(String.valueOf(movie.getYear()));

        //setting genre:
        genreTextView.setText(movie.getGenre());

        //setting poster:
        if (movie.getPosterResource() != null && !movie.getPosterResource().isEmpty()) {
            int resourceId = itemView.getContext().getResources().getIdentifier(movie.getPosterResource(), "drawable", itemView.getContext().getPackageName());
            if (resourceId != 0) posterImageView.setImageResource(resourceId);
        }
    }
}
