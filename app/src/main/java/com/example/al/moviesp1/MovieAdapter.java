package com.example.al.moviesp1;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends ArrayAdapter<MovieInfo> {

    public MovieAdapter(Activity context, ArrayList<MovieInfo> movieInfo) {
        super(context, 0, movieInfo);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.i("TEST", Integer.valueOf(this.getItemViewType(position)).toString() );

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_item, parent, false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_image);

        Picasso.with(getContext())
                .load("http://image.tmdb.org/t/p/w185/" + this.getItem(position).posterPath)
                .placeholder(R.drawable.user_placeholder)
                .error(R.drawable.user_placeholder_error)
                .into(imageView);

        Log.i("sort1", String.valueOf(position)+" "+this.getItem(position).posterPath);
        return convertView;
    }
}
