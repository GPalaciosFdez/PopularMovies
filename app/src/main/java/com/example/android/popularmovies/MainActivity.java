package com.example.android.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
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
import com.example.android.popularmovies.utilities.NetworkUtils;
import com.example.android.popularmovies.utilities.ParcelableMovie;
import com.example.android.popularmovies.utilities.PopularMoviesJsonUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterOnClickHandler{

    private RecyclerView mRecyclerView;

    private MoviesAdapter mMoviesAdapter;

    private ProgressBar mProgressIndicator;
    private TextView mErrorTextView;

    private String sortBy = "popularity";

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

        loadMoviesData();

    }

    private void loadMoviesData(){
        ShowMovies();
        new FetchMoviesTask().execute(sortBy);
    }

    private void ShowMovies(){
        mErrorTextView.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showError(){
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(ParcelableMovie movieInfo) {
        Intent intentToDetail = new Intent(this, MovieDetailActivity.class);
        intentToDetail.putExtra(Intent.EXTRA_TEXT, movieInfo);
        startActivity(intentToDetail);
    }

    public class FetchMoviesTask extends AsyncTask<String, Void, ParcelableMovie[]>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected ParcelableMovie[] doInBackground(String... params) {

            if(params.length == 0){
                return null;
            }

            String sortBy = params[0];
            URL requestUrl = NetworkUtils.buildMovieDataUrl(sortBy);

            try {
                String moviesJsonResponse = NetworkUtils
                        .getResponseFromHttpUrl(requestUrl);
                return PopularMoviesJsonUtils.getMoviesArrayFromJson(moviesJsonResponse);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(ParcelableMovie[] movies) {
            mProgressIndicator.setVisibility(View.INVISIBLE);
            if(movies != null){
                mMoviesAdapter.setmMoviesData(movies);
            }
            else{
                showError();
            }
        }
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
            sortBy = "popularity";
            loadMoviesData();
            return true;
        }
        if(item.getItemId() == R.id.action_rated){
            sortBy = "vote_average";
            loadMoviesData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
