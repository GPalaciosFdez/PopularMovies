package com.example.android.popularmovies.utilities;

import android.os.AsyncTask;

import com.example.android.popularmovies.MovieDetailActivity;

import java.net.URL;

/**
 * Created by GPalacios on 12/02/17.
 */

public class FetchTrailersTask extends AsyncTask<ParcelableMovie, Void, ParcelableMovie> {

    private MovieDetailActivity movieDetailActivity;

    public FetchTrailersTask(MovieDetailActivity movieDetailActivity) {
        this.movieDetailActivity = movieDetailActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ParcelableMovie doInBackground(ParcelableMovie... params) {
        if (params.length == 0) {
            return null;
        }

        ParcelableMovie movie = params[0];

        String id = movie.getId();
        URL trailerRequestUrl = NetworkUtils.buildTrailerUrl(id);
        URL reviewRequestUrl = NetworkUtils.buildReviewUrl(id);

        try {
            String trailersJsonResponse = NetworkUtils.getResponseFromHttpUrl(trailerRequestUrl);
            String reviewsJsonResponse = NetworkUtils.getResponseFromHttpUrl(reviewRequestUrl);

            PopularMoviesJsonUtils.setTrailersAndReviews(movie, trailersJsonResponse, reviewsJsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return movie;
    }

    @Override
    protected void onPostExecute(ParcelableMovie movie) {

        if (movie.getTrailers().size() != 0) {
            movieDetailActivity.populateTrailers(movie);
        }
        if (movie.getReviews().size() != 0) {
            movieDetailActivity.populateReviews(movie);
        }

    }

}
