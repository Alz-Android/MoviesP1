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
    String popularity;
    String releaseDate;

    public MovieInfo(String mPoster, String mTitle, String mPlot, String mUserRating, String mPopularity,String mReleaseDate )
    {
        Log.i("MainActivityFragmen", "movie object");
        this.posterPath = mPoster;
        this.title = mTitle;
        this.plot = mPlot;
        this.userRating = mUserRating+"/10";
        this.popularity = mPopularity;
        this.releaseDate = mReleaseDate.substring(0,4);
    }
}
