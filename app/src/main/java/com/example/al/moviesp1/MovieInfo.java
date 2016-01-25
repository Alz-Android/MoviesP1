package com.example.al.moviesp1;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by Al on 2016-01-07.
 */
public class MovieInfo implements Parcelable {

    String posterPath;
    String title;
    String plot;
    String userRating;
    String popularity;
    String releaseDate;


    // Each movieInfoList objects holds one movie's information that is later put into an array of movieInfoList objects
    //Parcelable was implemented for better performance than Serializable.

    public MovieInfo(String mPoster, String mTitle, String mPlot, String mUserRating, String mPopularity,String mReleaseDate )
    {
        Log.i("MainActivityFragmen", "movie object");
        this.posterPath = mPoster;
        this.title = mTitle;
        this.plot = mPlot;
        this.userRating = mUserRating+"/10";
        this.popularity = mPopularity;
        if(!mReleaseDate.isEmpty())
            this.releaseDate = mReleaseDate.substring(0,4);
        else
            this.releaseDate = "n/a";

    }

    public MovieInfo(Parcel source) {
        posterPath = source.readString();
        title = source.readString();
        plot = source.readString();
        userRating = source.readString();
        popularity = source.readString();
        releaseDate = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterPath);
        dest.writeString(title);
        dest.writeString(plot);
        dest.writeString(userRating);
        dest.writeString(popularity);
        dest.writeString(releaseDate);
    }

    public static final Creator<MovieInfo> CREATOR = new Creator<MovieInfo>() {
        @Override
        public MovieInfo[] newArray(int size) {
            return new MovieInfo[size];
        }

        @Override
        public MovieInfo createFromParcel(Parcel source) {
            return new MovieInfo(source);
        }
    };
}
