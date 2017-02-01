package com.example.android.popularmovies.utilities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GPalacios on 31/01/17.
 */

public class ParcelableMovie implements Parcelable {

    private String title;
    private String releaseDate;
    private String pathToPoster;
    private String voteAverage;
    private String synopsis;

    ParcelableMovie(String movieTitle, String movieReleaseDate, String moviePathToPoster, String movieVoteAverage, String movieSynopsis){
        this.title = movieTitle;
        this.releaseDate = movieReleaseDate;
        this.pathToPoster = moviePathToPoster;
        this.voteAverage = movieVoteAverage;
        this.synopsis = movieSynopsis;
    }

    private ParcelableMovie(Parcel in) {
        title = in.readString();
        releaseDate = in.readString();
        pathToPoster = in.readString();
        voteAverage = in.readString();
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
        dest.writeString(voteAverage);
        dest.writeString(synopsis);
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getPathToPoster() {
        return pathToPoster;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }
}
