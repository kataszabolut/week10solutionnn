package com.example.week10solution;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    public static List<Movie> loadMoviesFromJson(Context context, int resourceId) throws IOException {

        //reading json
        String jsonContent;
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream is = context.getResources().openRawResource(resourceId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line);
            }
        }
        catch (IOException e){throw e;}

        jsonContent = stringBuilder.toString();

        List<Movie> movies = new ArrayList<>();
        try {

            //creating jsonarray
            JSONArray jsonArray = new JSONArray(jsonContent);

            //iterating thorugh json objects
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject movieObject = jsonArray.optJSONObject(i);

                //if the entry is empty
                if (movieObject == null) {
                    movies.add(new Movie("No movie data provided", 0, "?", null));
                    continue;
                }

                //finding title
                String title = movieObject.optString("title", "No title found");
                if (title.equals("null")) title = "No title found";

                //finding year
                int year = 0;
                Object yearFound = movieObject.opt("year");

                //if it is a number:
                if (yearFound instanceof Number) year = ((Number) yearFound).intValue();

                //or if it is a string:
                else if (yearFound instanceof String) {
                    try {year = Integer.parseInt((String) yearFound);}
                    catch (NumberFormatException e) {year = 0;}
                }

                //finding genre
                String genre = movieObject.optString("genre", "No genre found");

                //finding poster
                String poster = movieObject.optString("poster", "default_poster");

                //adding it to the list
                movies.add(new Movie(title, year, genre, poster));
            }

        }
        catch (Exception e) {handleJsonException(e);}
        return movies;
    }

    private static void handleJsonException(Exception exception){
        Log.e("JSONUtil", "JSON error occurred: " + exception.getMessage());
    }
}
