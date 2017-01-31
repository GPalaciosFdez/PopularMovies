package com.example.android.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by gpalacios on 31/01/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder>{

    private String[] mMoviesData; //TODO: change to ParcelableMovie[]

    private final MoviesAdapterOnClickHandler mClickHandler;

    public interface MoviesAdapterOnClickHandler{
        void onClick(String movieInfo);
    }

    public MoviesAdapter(MoviesAdapterOnClickHandler clickHandler){
        mClickHandler = clickHandler;
    }

    @Override
    public int getItemCount() {
        if(mMoviesData == null){
            return 0;
        }
        return mMoviesData.length;
    }

    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movies_grid_item, parent, false);

        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesAdapterViewHolder holder, int position) {
        //TODO 4: Bind posters to holders using the info pass from the async task
        String movie = mMoviesData[position];
        holder.mMovieTextView.setText(movie);
    }

    public void setmMoviesData(String[] moviesData) {
        moviesData = mMoviesData;
        notifyDataSetChanged();
    }

    class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //TODO: change TextView for View with movie image
        public final TextView mMovieTextView;

        public MoviesAdapterViewHolder(View view){
            super(view);
            mMovieTextView = (TextView) view.findViewById(R.id.tv_movie_data);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String movieInfo = mMoviesData[adapterPosition];
            //TODO 5: pass to MainActivity.onClick() the ParcelableMovie clicked from the array
            mClickHandler.onClick(movieInfo);
        }
    }

}
