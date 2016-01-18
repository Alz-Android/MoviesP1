package com.example.al.moviesp1;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Al on 2016-01-07.
 */
public class MovieInfo implements Serializable {

    String posterPath;
    String title;
    String plot;
    String userRating;
    String releaseDate;
    String popularity;


    public MovieInfo(String mPoster, String mTitle, String mPlot, String mUserRating, String mReleaseDate, String mPopularity)
    {
        Log.i("MainActivityFragmen", "movie object");

        this.posterPath = mPoster;
        this.title = mTitle;
        this.plot = mPlot;
        this.userRating = mUserRating;
        this.releaseDate = mReleaseDate;
        this.popularity = mPopularity;

        Log.i("MainActivityFragmen", "movie object2");
    }
}
