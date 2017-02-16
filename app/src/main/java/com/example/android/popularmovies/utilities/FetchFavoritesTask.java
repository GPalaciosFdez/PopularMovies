package com.example.android.popularmovies.utilities;

import android.database.Cursor;
import android.os.AsyncTask;
import android.view.View;

import com.example.android.popularmovies.MainActivity;
import com.example.android.popularmovies.data.FavoritesColumns;
import com.example.android.popularmovies.data.FavoritesProvider;

/**
 * Created by GPalacios on 16/02/17.
 */

public class FetchFavoritesTask extends AsyncTask<Void, ParcelableMovie[], Cursor> {

    private MainActivity mainActivity;

    public FetchFavoritesTask(MainActivity activity) {
        this.mainActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mainActivity.setVisibilityProgressIndicator(View.VISIBLE);
    }

    @Override
    protected Cursor doInBackground(Void... params) {

        return mainActivity.getContentResolver().query(FavoritesProvider.Favorites.CONTENT_URI, null, null, null, null);
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        super.onPostExecute(cursor);
        mainActivity.setVisibilityProgressIndicator(View.INVISIBLE);

        ParcelableMovie[] favorites = new ParcelableMovie[cursor.getCount()];

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(FavoritesColumns.ID));
            String title = cursor.getString(cursor.getColumnIndex(FavoritesColumns.TITLE));
            String releaseDate = cursor.getString(cursor.getColumnIndex(FavoritesColumns.RELEASE_DATE));
            String pathToPoster = cursor.getString(cursor.getColumnIndex(FavoritesColumns.PATH_TO_POSTER));
            String voteAverage = cursor.getString(cursor.getColumnIndex(FavoritesColumns.VOTE_AVERAGE));
            String synopsis = cursor.getString(cursor.getColumnIndex(FavoritesColumns.SYNOPSIS));

            ParcelableMovie movie = new ParcelableMovie(id, title, releaseDate, pathToPoster, voteAverage, synopsis);

            favorites[cursor.getPosition()] = movie;
        }

        cursor.close();

        if (favorites.length != 0) {
            mainActivity.passDataToAdapter(favorites);
        } else {
            mainActivity.showError();
        }

    }
}
