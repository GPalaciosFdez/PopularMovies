package com.example.android.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.popularmovies.utilities.FetchTrailersTask;
import com.example.android.popularmovies.utilities.ParcelableMovie;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView mMovieTitle;
    private TextView mReleaseDate;
    private ImageView mMoviePoster;
    private TextView mVoteAverage;
    private TextView mSynopsis;
    private LinearLayout mTrailers;
    private LinearLayout mReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mMovieTitle = (TextView) findViewById(R.id.tv_movie_title);
        mReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        mVoteAverage = (TextView) findViewById(R.id.tv_vote_average);
        mSynopsis = (TextView) findViewById(R.id.tv_synopsis);
        mMoviePoster = (ImageView) findViewById(R.id.iv_movie_poster);
        mTrailers = (LinearLayout) findViewById(R.id.ll_trailers);
        mReviews = (LinearLayout) findViewById(R.id.ll_reviews);

        Intent intentThatStartedThisActivity = getIntent();

        if(intentThatStartedThisActivity != null){
            if(intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
                ParcelableMovie movieData = intentThatStartedThisActivity.getParcelableExtra(Intent.EXTRA_TEXT);
                mMovieTitle.setText(movieData.getTitle());
                mReleaseDate.setText(movieData.getReleaseDate());
                mVoteAverage.setText(movieData.getVoteAverage());
                mSynopsis.setText(movieData.getSynopsis());

                Picasso.with(this)
                        .load(movieData.getPathToPoster())
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                        .resize(450, 650)
                        .into(mMoviePoster);

                new FetchTrailersTask(this).execute(movieData);
            }
        }

    }

    public void populateTrailers(ParcelableMovie movie) {
        for (int i = 0; i < movie.getTrailers().size() - 1; i++) {
            TextView child = (TextView) getLayoutInflater().inflate(R.layout.detail_child, null);
            child.setText("TRAILER " + i);
            child.setTag(movie.getTrailers().get(i));

            mTrailers.addView(child);
        }
    }

    public void populateReviews(ParcelableMovie movie) {
        int count = 0;
        for (String review : movie.getReviews()) {
            count++;
            TextView child = (TextView) getLayoutInflater().inflate(R.layout.detail_child, null);
            child.setText("REVIEW " + count);
            child.setTag(review);

            mReviews.addView(child);
        }
    }

    public void onClickChild(View view) {
        if (view.getTag() != null) {
            Uri uri = Uri.parse((String) view.getTag());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }
}
