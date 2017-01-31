package com.example.android.popularmovies.utilities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GPalacios on 31/01/17.
 */

public class ParcelableMovie implements Parcelable {

    String title;
    String releaseDate;
    String pathToPoster;
    float voteAverage;
    String synopsis;

    protected ParcelableMovie(Parcel in) {
        title = in.readString();
        releaseDate = in.readString();
        pathToPoster = in.readString();
        voteAverage = in.readFloat();
        synopsis = in.readString();
    }

    public static final Creator<ParcelableMovie> CREATOR = new Creator<ParcelableMovie>() {
        @Override
        public ParcelableMovie createFromParcel(Parcel in) {
            return new ParcelableMovie(in);
        }

        @Override
        public ParcelableMovie[] newArray(int size) {
            return new ParcelableMovie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(releaseDate);
        dest.writeString(pathToPoster);
        dest.writeFloat(voteAverage);
        dest.writeString(synopsis);
    }
}
