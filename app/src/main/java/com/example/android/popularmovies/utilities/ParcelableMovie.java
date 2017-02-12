package com.example.android.popularmovies.utilities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by GPalacios on 31/01/17.
 */


public class ParcelableMovie implements Parcelable {

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
    private String title;
    private String releaseDate;
    private String pathToPoster;
    private String voteAverage;
    private String synopsis;
    private String id;
    private ArrayList<String> trailers;
    private ArrayList<String> reviews;

    ParcelableMovie(String movieId, String movieTitle, String movieReleaseDate, String moviePathToPoster, String movieVoteAverage, String movieSynopsis) {
        this.id = movieId;
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
        id = in.readString();
        trailers = in.createStringArrayList();
        reviews = in.createStringArrayList();
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

    public ArrayList<String> getTrailers() {
        return trailers;
    }

    void setTrailers(ArrayList<String> trailers) {
        this.trailers = trailers;
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getReviews() {
        return reviews;
    }

    void setReviews(ArrayList<String> reviews) {
        this.reviews = reviews;
    }

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
        dest.writeString(id);
        dest.writeStringList(trailers);
        dest.writeStringList(reviews);
    }
}
