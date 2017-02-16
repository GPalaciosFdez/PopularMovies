package com.example.android.popularmovies.data;

import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

/**
 * Created by GPalacios on 15/02/17.
 */

public interface FavoritesColumns {

    @DataType(DataType.Type.TEXT)
    @PrimaryKey
    @NotNull
    String ID = "id";
    @DataType(DataType.Type.TEXT)
    @PrimaryKey
    @NotNull
    String TITLE = "title";
    @DataType(DataType.Type.TEXT)
    @PrimaryKey
    String RELEASE_DATE = "release_date";
    @DataType(DataType.Type.TEXT)
    @PrimaryKey
    String PATH_TO_POSTER = "path_to_poster";
    @DataType(DataType.Type.TEXT)
    @PrimaryKey
    String VOTE_AVERAGE = "vote_average";
    @DataType(DataType.Type.TEXT)
    @PrimaryKey
    String SYNOPSIS = "synopsis";
    @DataType(DataType.Type.INTEGER)
    @PrimaryKey
    String IS_FAVORITE = "is_favorite";


}
