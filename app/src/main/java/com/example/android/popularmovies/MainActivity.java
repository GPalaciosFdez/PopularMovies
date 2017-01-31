package com.example.android.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.popularmovies.utilities.AutofitGridLayoutManager;
import com.example.android.popularmovies.utilities.ParcelableMovie;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterOnClickHandler{

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;

    private MoviesAdapter mMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_movies);

        GridLayoutManager layoutManager = new AutofitGridLayoutManager(this, 342);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mMoviesAdapter = new MoviesAdapter(this);
        mRecyclerView.setAdapter(mMoviesAdapter);

    }
    //TODO: Put info in an intent and start new activity
    @Override
    public void onClick(String movieInfo) {
        Intent intentToDetail = new Intent(this, MovieDetailActivity.class);
        //TODO 6: Pass the ParcelableMovie received from MoviesAdapter to DetailActivity
        intentToDetail.putExtra(Intent.EXTRA_TEXT, movieInfo);
        startActivity(intentToDetail);
    }

    public class FetchMoviesTask extends AsyncTask<String, Void, ParcelableMovie[]>{

        @Override
        protected ParcelableMovie[] doInBackground(String... params) {
            //TODO 1: get the info from the api
            //TODO 2: parse the info into a ParcelableMovie[]
            return new ParcelableMovie[];
        }

        @Override
        protected void onPostExecute(ParcelableMovie[] parcelableMovies) {
            //TODO 3: MoviesAdapter.setMoviesData (pas the data to adapter)
            super.onPostExecute(parcelableMovies);
        }
    }

}
