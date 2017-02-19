package com.example.android.popularmovies;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.popularmovies.data.FavoritesContract;
import com.example.android.popularmovies.utilities.FetchTrailersTask;
import com.example.android.popularmovies.utilities.ParcelableMovie;
import com.squareup.picasso.Picasso;


public class MovieDetailActivity extends AppCompatActivity {

    private final String TAG = MovieDetailActivity.class.getSimpleName();
    ParcelableMovie movieData;
    private LinearLayout mTrailers;
    private LinearLayout mReviews;
    private Button mFavoriteButton;
    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        TextView mMovieTitle = (TextView) findViewById(R.id.tv_movie_title);
        TextView mReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        TextView mVoteAverage = (TextView) findViewById(R.id.tv_vote_average);
        TextView mSynopsis = (TextView) findViewById(R.id.tv_synopsis);
        ImageView mMoviePoster = (ImageView) findViewById(R.id.iv_movie_poster);
        mTrailers = (LinearLayout) findViewById(R.id.ll_trailers);
        mReviews = (LinearLayout) findViewById(R.id.ll_reviews);
        mFavoriteButton = (Button) findViewById(R.id.button_favorite);

        Intent intentThatStartedThisActivity = getIntent();

        if(intentThatStartedThisActivity != null){
            if(intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
                movieData = intentThatStartedThisActivity.getParcelableExtra(Intent.EXTRA_TEXT);
                mMovieTitle.setText(movieData.getTitle());
                mReleaseDate.setText(movieData.getReleaseDate());
                mVoteAverage.setText(movieData.getVoteAverage());
                mSynopsis.setText(movieData.getSynopsis());

                isFavorite = (movieData.getIsFavorite() == 1);

                if (!isFavorite) {
                    if (getContentResolver().query(FavoritesContract.FavoritesEntry.buildUri(movieData.getId()), null, null, null, null).moveToFirst()) {
                        isFavorite = true;
                    } else {
                        mFavoriteButton.setText(getString(R.string.addFavorite));
                    }


                }
                if (isFavorite) {
                    mFavoriteButton.setText(getString(R.string.deleteFavorite));
                }


                Picasso.with(this)
                        .load(movieData.getPathToPoster())
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                        .resize(450, 650)
                        .into(mMoviePoster);

                new FetchTrailersTask(this).execute(movieData);
            }
        }

        mFavoriteButton.setClickable(true);
    }

    public void populateTrailers(ParcelableMovie movie) {
        for (int i = 0; i < movie.getTrailers().size() - 1; i++) {
            TextView child = (TextView) getLayoutInflater().inflate(R.layout.detail_child, null);
            child.setText(getString(R.string.trailer) + i);
            child.setTag(movie.getTrailers().get(i));

            mTrailers.addView(child);
        }
    }

    public void populateReviews(ParcelableMovie movie) {
        int count = 0;
        for (String review : movie.getReviews()) {
            count++;
            TextView child = (TextView) getLayoutInflater().inflate(R.layout.detail_child, null);
            child.setText(getString(R.string.review) + count);
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

    public void handleFavorite(View view) {
        boolean favorite = isFavorite;
        if (favorite) {
            deleteFavorite(movieData);
            isFavorite = false;
            mFavoriteButton.setText(getString(R.string.addFavorite));
        }
        if (!favorite) {
            addFavorite(movieData);
            isFavorite = true;
            mFavoriteButton.setText(getString(R.string.deleteFavorite));
        }
    }

    private void addFavorite(ParcelableMovie movieData) {

        ContentValues values = new ContentValues();

        values.put(FavoritesContract.FavoritesEntry.COLUMN_MOVIE_ID, movieData.getId());
        values.put(FavoritesContract.FavoritesEntry.COLUMN_TITLE, movieData.getTitle());
        values.put(FavoritesContract.FavoritesEntry.COLUMN_RELEASE_DATE, movieData.getReleaseDate());
        values.put(FavoritesContract.FavoritesEntry.COLUMN_PATH_TO_POSTER, movieData.getPathToPoster());
        values.put(FavoritesContract.FavoritesEntry.COLUMN_VOTE_AVERAGE, movieData.getVoteAverage());
        values.put(FavoritesContract.FavoritesEntry.COLUMN_SYNOPSIS, movieData.getSynopsis());
        values.put(FavoritesContract.FavoritesEntry.COLUMN_IS_FAVORITE, 1);


        try {
            getContentResolver().insert(FavoritesContract.FavoritesEntry.CONTENT_URI, values);
        } catch (Exception e) {
            Log.e(TAG, "Error inserting", e);
        }
    }

    private int deleteFavorite(ParcelableMovie movieData) {

        String[] id = new String[]{movieData.getId()};

        return getContentResolver().delete(FavoritesContract.FavoritesEntry.buildUri(movieData.getId()), FavoritesContract.FavoritesEntry.COLUMN_MOVIE_ID + "=?", id);

    }


}
