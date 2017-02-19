package com.example.android.popularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GPalacios on 19/02/17.
 */

class FavoritesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favorites.db";

    private static final int DATABASE_VERSION = 3;

    FavoritesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_WEATHER_TABLE =

                "CREATE TABLE " + FavoritesContract.FavoritesEntry.TABLE_NAME + " (" +

                        FavoritesContract.FavoritesEntry.COLUMN_MOVIE_ID + " TEXT PRIMARY KEY , " +
                        FavoritesContract.FavoritesEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                        FavoritesContract.FavoritesEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL," +
                        FavoritesContract.FavoritesEntry.COLUMN_PATH_TO_POSTER + " TEXT, " +
                        FavoritesContract.FavoritesEntry.COLUMN_VOTE_AVERAGE + " TEXT, " +
                        FavoritesContract.FavoritesEntry.COLUMN_SYNOPSIS + " TEXT, " +
                        FavoritesContract.FavoritesEntry.COLUMN_IS_FAVORITE + " INTEGER, " +

                        " UNIQUE (" + FavoritesContract.FavoritesEntry.COLUMN_MOVIE_ID + ") ON CONFLICT REPLACE);";

        db.execSQL(SQL_CREATE_WEATHER_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FavoritesContract.FavoritesEntry.TABLE_NAME);
        onCreate(db);
    }
}
