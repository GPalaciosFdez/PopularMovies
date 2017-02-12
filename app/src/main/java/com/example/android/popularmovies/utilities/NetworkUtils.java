package com.example.android.popularmovies.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by gpalacios on 31/01/17.
 */

public final class NetworkUtils {

    private static final String API_KEY = "";

    private static final String MOVIE_IMG_BASE_URL = "http://image.tmdb.org/t/p";
    private static final String MOVIE_IMG_FILE_SIZE = "w185";

    private static final String MOVIE_DATA_BASE_URL = "http://api.themoviedb.org/3/";
    private static final String MOVIE_DISCOVER_PATH = "discover";

    private static final String MOVIE_MOVIE_PATH = "movie";
    private static final String MOVIE_TRAILER_PATH = "videos";
    private static final String MOVIE_REVIEW_PATH = "reviews";

    static URL buildMovieDataUrl(String query) {
        Uri builtUri = Uri.parse(MOVIE_DATA_BASE_URL).buildUpon()
                .appendPath(MOVIE_DISCOVER_PATH)
                .appendPath(MOVIE_MOVIE_PATH)
                .appendQueryParameter("sort_by", query + ".desc")
                .appendQueryParameter("api_key", API_KEY)
                .build();


        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    static String buildMovieImageUrl(String path) {
        return MOVIE_IMG_BASE_URL + "/" + MOVIE_IMG_FILE_SIZE + "/" + path;
    }

    static URL buildTrailerUrl(String id) {
        Uri builtUri = Uri.parse(MOVIE_DATA_BASE_URL).buildUpon()
                .appendPath(MOVIE_MOVIE_PATH)
                .appendPath(id)
                .appendPath(MOVIE_TRAILER_PATH)
                .appendQueryParameter("api_key", API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    static URL buildReviewUrl(String id) {
        Uri builtUri = Uri.parse(MOVIE_DATA_BASE_URL).buildUpon()
                .appendPath(MOVIE_MOVIE_PATH)
                .appendPath(id)
                .appendPath(MOVIE_REVIEW_PATH)
                .appendQueryParameter("api_key", API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
