package com.example.android.popularmovies.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by GPalacios on 31/01/17.
 */

public final class PopularMoviesJsonUtils {

    public static JSONArray getStringArrayFromJson(String data) throws JSONException{

        JSONObject dataJson = new JSONObject(data);

        JSONArray resultsArray = dataJson.getJSONArray("results");

    }

}
