package com.example.android.popularmovies.utilities;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by GPalacios on 31/01/17.
 */

public final class PopularMoviesJsonUtils {

    public static ParcelableMovie[] getMoviesArrayFromJson(String data) throws JSONException{

        JSONObject dataJson = new JSONObject(data);

        JSONArray resultsArray = dataJson.getJSONArray("results");

        ParcelableMovie[] movies = new ParcelableMovie[resultsArray.length()];

        for(int i = 0; i<resultsArray.length(); i++){
            String title, releaseDate, pathToPoster, fullPathToPoster, synopsis, voteAverage;

            title = resultsArray.getJSONObject(i).getString("original_title");
            releaseDate = resultsArray.getJSONObject(i).getString("release_date");
            pathToPoster = resultsArray.getJSONObject(i).getString("poster_path");
            fullPathToPoster = NetworkUtils.buildMovieImageUrl(pathToPoster);
            synopsis = resultsArray.getJSONObject(i).getString("overview");
            voteAverage = Double.toString(resultsArray.getJSONObject(i).getDouble("vote_average"));

            movies[i] = new ParcelableMovie(title, releaseDate, fullPathToPoster, voteAverage, synopsis);
        }

        return movies;

    }

}
