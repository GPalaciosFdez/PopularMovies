package com.example.android.popularmovies.utilities;

import android.os.AsyncTask;
import android.view.View;

import com.example.android.popularmovies.MainActivity;

import java.net.URL;

/**
 * Created by GPalacios on 01/02/17.
 */
public class FetchMoviesTask extends AsyncTask<String, Void, ParcelableMovie[]> {

    private MainActivity mainActivity;

    public FetchMoviesTask(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mainActivity.setVisibilityProgressIndicator(View.VISIBLE);
    }

    @Override
    protected ParcelableMovie[] doInBackground(String... params) {

        if (params.length == 0) {
            return null;
        }

        String sortBy = params[0];
        URL requestUrl = NetworkUtils.buildMovieDataUrl(sortBy);

        try {
            String moviesJsonResponse = NetworkUtils
                    .getResponseFromHttpUrl(requestUrl);
            return PopularMoviesJsonUtils.getMoviesArrayFromJson(moviesJsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(ParcelableMovie[] movies) {
        mainActivity.setVisibilityProgressIndicator(View.INVISIBLE);
        if (movies != null) {
            mainActivity.passDataToAdapter(movies);
        } else {
            mainActivity.showError();
        }
    }
}
