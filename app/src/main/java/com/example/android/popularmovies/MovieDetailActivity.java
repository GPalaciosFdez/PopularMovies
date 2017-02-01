package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.utilities.ParcelableMovie;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView mMovieTitle;
    private TextView mReleaseDate;
    private ImageView mMoviePoster;
    private TextView mVoteAverage;
    private TextView mSynopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mMovieTitle = (TextView) findViewById(R.id.tv_movie_title);
        mReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        mVoteAverage = (TextView) findViewById(R.id.tv_vote_average);
        mSynopsis = (TextView) findViewById(R.id.tv_synopsis);
        mMoviePoster = (ImageView) findViewById(R.id.iv_movie_poster);

        Intent intentThatStartedThisActivity = getIntent();

        if(intentThatStartedThisActivity != null){
            if(intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
                ParcelableMovie movieData = intentThatStartedThisActivity.getParcelableExtra(Intent.EXTRA_TEXT);
                mMovieTitle.setText(movieData.getTitle());
                mReleaseDate.setText(movieData.getReleaseDate());
                mVoteAverage.setText(movieData.getVoteAverage());
                mSynopsis.setText(movieData.getSynopsis());

                Picasso.with(this).load(movieData.getPathToPoster()).resize(450, 650).into(mMoviePoster);
            }
        }

    }
}
