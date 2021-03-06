package com.example.al.moviesp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
            // Creating the intent to rcv data from the Main Activity
            // and attaching the data to the appropriate Views in Detail Activity
            Intent intent = getActivity().getIntent();
            View rootView = inflater.inflate(R.layout.fragment_detail_activity, container, false);

            if(intent != null) {
                MovieInfo movieObj = (MovieInfo)intent.getParcelableExtra("movie");
                ((TextView)rootView.findViewById(R.id.title_text)).setText(movieObj.title);
                ((TextView)rootView.findViewById(R.id.releaseDate_text)).setText(movieObj.releaseDate);
                ((TextView)rootView.findViewById(R.id.userRating_text)).setText(movieObj.userRating);
                ((TextView)rootView.findViewById(R.id.plot_text)).setText(movieObj.plot);

                ImageView imageView = (ImageView) rootView.findViewById(R.id.movie_image);

                Picasso.with(getContext())
                        .load("http://image.tmdb.org/t/p/w185/" + movieObj.posterPath)
                        .placeholder(R.drawable.user_placeholder)
                        .error(R.drawable.user_placeholder_error)
                        .into(imageView);
            }
            return rootView;
        }
    }
}
