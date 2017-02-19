package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popularmovies.utilities.AutofitGridLayoutManager;
import com.example.android.popularmovies.utilities.FetchFavoritesTask;
import com.example.android.popularmovies.utilities.FetchMoviesTask;
import com.example.android.popularmovies.utilities.ParcelableMovie;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterOnClickHandler{

    private static final int INDEX_POPULAR = 0;
    private static final int INDEX_RATING = 1;
    private static final int INDEX_FAVORITES = 2;
    private static final String SORT_BY_KEY = "sortBy";
    private RecyclerView mRecyclerView;
    private MoviesAdapter mMoviesAdapter;
    private ProgressBar mProgressIndicator;
    private TextView mErrorTextView;
    private int saveSorting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressIndicator = (ProgressBar) findViewById(R.id.pb_loading);
        mErrorTextView = (TextView) findViewById(R.id.tv_error);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_movies);

        GridLayoutManager layoutManager = new AutofitGridLayoutManager(this, 500);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mMoviesAdapter = new MoviesAdapter(this);
        mRecyclerView.setAdapter(mMoviesAdapter);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(SORT_BY_KEY)) {
                saveSorting = savedInstanceState.getInt(SORT_BY_KEY);
            } else {
                saveSorting = 0;
            }
        }

        loadMoviesData(saveSorting);

    }

    private void loadMoviesData(int saveSorting) {
        ShowMovies();
        if (saveSorting == INDEX_FAVORITES) {
            new FetchFavoritesTask(this).execute();
        } else {
            String sortBy = (saveSorting == INDEX_POPULAR) ? "popular" : "top_rated";
            new FetchMoviesTask(this).execute(sortBy);
        }
    }

    private void ShowMovies(){
        mErrorTextView.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    public void showError(){
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    public void passDataToAdapter(ParcelableMovie[] movies){
        mMoviesAdapter.setmMoviesData(movies);
    }

    public void setVisibilityProgressIndicator(int visibility){
        mProgressIndicator.setVisibility(visibility);
    }

    @Override
    public void onClick(ParcelableMovie movieInfo) {
        Intent intentToDetail = new Intent(this, MovieDetailActivity.class);
        intentToDetail.putExtra(Intent.EXTRA_TEXT, movieInfo);
        startActivity(intentToDetail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_by, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_popularity){
            saveSorting = INDEX_POPULAR;
            loadMoviesData(saveSorting);
            return true;
        }
        if(item.getItemId() == R.id.action_rated){
            saveSorting = INDEX_RATING;
            loadMoviesData(saveSorting);
            return true;
        }
        if (item.getItemId() == R.id.action_favorite) {
            saveSorting = INDEX_FAVORITES;
            loadMoviesData(saveSorting);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SORT_BY_KEY, saveSorting);
    }

}
