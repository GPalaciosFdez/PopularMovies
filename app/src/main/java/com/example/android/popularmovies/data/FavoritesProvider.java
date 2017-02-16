package com.example.android.popularmovies.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by GPalacios on 15/02/17.
 */

@ContentProvider(authority = FavoritesProvider.AUTHORITY, database = FavoritesDatabase.class)
public final class FavoritesProvider {

    static final String AUTHORITY = "com.example.android.popularmovies.data.FavoritesProvider";

    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    private static Uri buildUri(String... paths) {
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }
        return builder.build();
    }

    interface Path {
        String FAVORITES = "favorites";
    }

    @TableEndpoint(table = FavoritesDatabase.FAVORITES)
    public static class Favorites {
        @ContentUri(
                path = Path.FAVORITES,
                type = "vdn.android.cursor.dir/favorite")

        public static final Uri CONTENT_URI = buildUri(Path.FAVORITES);

        @InexactContentUri(
                name = "FAVORITE_ID",
                path = Path.FAVORITES + "/*",
                type = "vnd.android.cursor.item/favorite",
                whereColumn = FavoritesColumns.ID,
                pathSegment = 1)

        public static Uri withId(String id) {
            return buildUri(Path.FAVORITES, id);
        }
    }

}
