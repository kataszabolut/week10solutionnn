package com.example.week10solution;

public class Movie {

    //attributes:
    private String title;
    private Integer year;
    private String genre;
    private String posterResource;

    //constructor:
    public Movie(String title, Integer year, String genre, String posterResource) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.posterResource = posterResource;
    }

    //accessors:
    public String getTitle() {return title;}
    public Integer getYear() {return year;}
    public String getGenre() {return genre;}
    public String getPosterResource() {return posterResource;}

}
