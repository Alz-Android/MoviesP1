package com.example.al.moviesp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Intent intent = getActivity().getIntent();
            View rootView = inflater.inflate(R.layout.fragment_detail_activity, container, false);
            if(intent != null) {
                MovieInfo movieObj = (MovieInfo)intent.getSerializableExtra("movie");   //getStringExtra(Intent.EXTRA_TEXT);

                Log.i("Detail", movieObj.title);

                ((TextView)rootView.findViewById(R.id.title_text)).setText(movieObj.title);
                ((TextView)rootView.findViewById(R.id.releaseDate_text)).setText(movieObj.releaseDate);
                ((TextView)rootView.findViewById(R.id.userRating_text)).setText(movieObj.userRating);
                ((TextView)rootView.findViewById(R.id.plot_text)).setText(movieObj.plot);

                ImageView imageView = (ImageView) rootView.findViewById(R.id.movie_image);

                Picasso.with(getContext())
                        .load("http://image.tmdb.org/t/p/w185/" + movieObj.posterPath)
                        .into(imageView);
            }
            return rootView;
        }
    }
}
