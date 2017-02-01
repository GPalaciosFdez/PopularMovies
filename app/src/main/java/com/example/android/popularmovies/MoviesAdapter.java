package com.example.android.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies.utilities.ParcelableMovie;
import com.squareup.picasso.Picasso;

/**
 * Created by gpalacios on 31/01/17.
 */

class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder>{

    private ParcelableMovie[] mMoviesData;

    private final MoviesAdapterOnClickHandler mClickHandler;

    interface MoviesAdapterOnClickHandler{
        void onClick(ParcelableMovie movieInfo);
    }

    MoviesAdapter(MoviesAdapterOnClickHandler clickHandler){
        mClickHandler = clickHandler;
    }

    @Override
    public int getItemCount() {
        if(mMoviesData == null){
            return 0;
        }
        int length = mMoviesData.length;
        String lengthStr = Integer.toString(length);
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
        String pathToPoster = mMoviesData[position].getPathToPoster();
        ImageView destination = holder.mMovieImageView;
        Picasso.with(destination.getContext()).load(pathToPoster).resize(450, 650).into(destination);
    }

    void setmMoviesData(ParcelableMovie[] moviesData) {
        mMoviesData = moviesData;
        notifyDataSetChanged();
    }

    class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //TODO: change TextView for View with movie image
        final ImageView mMovieImageView;

        MoviesAdapterViewHolder(View view){
            super(view);
            mMovieImageView = (ImageView) view.findViewById(R.id.iv_movie_poster);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            ParcelableMovie movieInfo = mMoviesData[adapterPosition];
            //TODO 5: pass to MainActivity.onClick() the ParcelableMovie clicked from the array
            mClickHandler.onClick(movieInfo);
        }
    }

}
