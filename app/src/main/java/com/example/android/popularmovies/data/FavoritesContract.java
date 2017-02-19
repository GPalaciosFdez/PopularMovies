package com.example.android.popularmovies.data;

import android.net.Uri;
import android.provider.BaseColumns;


/**
 * Created by GPalacios on 15/02/17.
 */

public class FavoritesContract {

    static final String CONTENT_AUTHORITY = "com.example.android.popularmovies";
    static final String PATH_FAVORITES = "favorites";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class FavoritesEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_FAVORITES)
                .build();
        public static final String COLUMN_MOVIE_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_PATH_TO_POSTER = "path_to_poster";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_SYNOPSIS = "synopsis";
        public static final String COLUMN_IS_FAVORITE = "is_favorite";
        static final String TABLE_NAME = "favorites";

        public static Uri buildUri(String... paths) {
            Uri.Builder builder = CONTENT_URI.buildUpon();
            for (String path : paths) {
                builder.appendPath(path);
            }
            return builder.build();
        }

    }

}
