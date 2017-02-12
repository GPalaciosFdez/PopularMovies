package com.example.android.popularmovies.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by GPalacios on 31/01/17.
 */

final class PopularMoviesJsonUtils {

    static ParcelableMovie[] getMoviesArrayFromJson(String data) throws JSONException {

        JSONObject dataJson = new JSONObject(data);

        JSONArray resultsArray = dataJson.getJSONArray("results");

        ParcelableMovie[] movies = new ParcelableMovie[resultsArray.length()];

        for(int i = 0; i<resultsArray.length(); i++){
            String id, title, releaseDate, pathToPoster, fullPathToPoster, synopsis, voteAverage;
            id = Integer.toString(resultsArray.getJSONObject(i).getInt("id"));
            title = resultsArray.getJSONObject(i).getString("original_title");
            releaseDate = resultsArray.getJSONObject(i).getString("release_date");
            pathToPoster = resultsArray.getJSONObject(i).getString("poster_path");
            fullPathToPoster = NetworkUtils.buildMovieImageUrl(pathToPoster);
            synopsis = resultsArray.getJSONObject(i).getString("overview");
            voteAverage = Double.toString(resultsArray.getJSONObject(i).getDouble("vote_average"));

            movies[i] = new ParcelableMovie(id, title, releaseDate, fullPathToPoster, voteAverage, synopsis);
        }

        return movies;

    }

    static void setTrailersAndReviews(ParcelableMovie movie, String trailersResponse, String reviewsResponse) throws JSONException {
        JSONObject trailerDataJson = new JSONObject(trailersResponse);
        JSONObject reviewDataJson = new JSONObject(reviewsResponse);

        JSONArray trailerResultsArray = trailerDataJson.getJSONArray("results");
        JSONArray reviewResultsArray = reviewDataJson.getJSONArray("results");

        ArrayList<String> trailers = new ArrayList<>();
        for (int i = 0; i < trailerResultsArray.length(); i++) {
            JSONObject movieTrailer = trailerResultsArray.getJSONObject(i);
            if (movieTrailer.getString("type").equals("Trailer")) {
                trailers.add("https://www.youtube.com/watch?v=" + movieTrailer.getString("key"));
            }
        }
        movie.setTrailers(trailers);

        ArrayList<String> reviews = new ArrayList<>();
        for (int i = 0; i < reviewResultsArray.length(); i++) {
            JSONObject movieReview = reviewResultsArray.getJSONObject(i);
            reviews.add(movieReview.getString("url"));
        }
        movie.setReviews(reviews);

    }

}
