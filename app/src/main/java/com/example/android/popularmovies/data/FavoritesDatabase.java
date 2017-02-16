package com.example.android.popularmovies.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by GPalacios on 15/02/17.
 */


@Database(version = FavoritesDatabase.VERSION)
public final class FavoritesDatabase {

    public static final int VERSION = 1;
    @Table(FavoritesColumns.class)
    public static final String FAVORITES = "favorites";

    private FavoritesDatabase() {
    }

}
