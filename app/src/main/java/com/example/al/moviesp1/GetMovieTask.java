package com.example.al.moviesp1;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Al on 1/26/2016.
 */
public class GetMovieTask extends AsyncTask<String, Void, String> {

    ArrayList<MovieInfo> movieInfoList = new ArrayList<>();

    private ArrayList<MovieInfo> getMovieDataFromJson(String movieJsonStr)
            throws JSONException {

        movieInfoList.clear();
        // These are the names of the JSON objects that need to be extracted.
        final String Movie_Results = "results";
        final String MOVIE_POSTER_PATH = "poster_path";
        final String MOVIE_TITLE = "title";
        final String MOVIE_PLOT = "overview";
        final String MOVIE_USER_RATINGS = "vote_average";
        final String MOVIE_POPULARITY = "popularity";
        final String MOVIE_RELEASE_DATE = "release_date";


        JSONObject moviesJson = new JSONObject(movieJsonStr);
        JSONArray moviesArray = moviesJson.getJSONArray(Movie_Results);

        for(int i = 0; i < moviesArray.length(); i++) {

            String posterPath="";
            String title="";
            String plot="";
            String userRatings="";
            String popularity="";
            String releaseDate="";

            // Get the JSON object representing a movie
            JSONObject movieObject = moviesArray.getJSONObject(i);
            posterPath = movieObject.getString(MOVIE_POSTER_PATH);
            title = movieObject.getString(MOVIE_TITLE);
            plot = movieObject.getString(MOVIE_PLOT);
            userRatings = movieObject.getString(MOVIE_USER_RATINGS);
            popularity = movieObject.getString(MOVIE_POPULARITY);
            releaseDate = movieObject.getString(MOVIE_RELEASE_DATE);

            movieInfoList.add(new MovieInfo(posterPath, title, plot, userRatings, popularity, releaseDate));
        }
        return movieInfoList;
    }


    @Override
    protected String doInBackground(String... params) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String movieJsonStr = null;

        try {
            final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
            final String SORT_PARAM = "sort_by";
            final String APIKEY_PARAM = "api_key";

            Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                    .appendQueryParameter(SORT_PARAM, params[0])
                    .appendQueryParameter(APIKEY_PARAM, BuildConfig.MOVIES_TMDB_API_KEY)
                    .build();

            URL url = new URL(builtUri.toString());

            Log.i("sort", "Query String: " + builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                movieJsonStr = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // new line to make debugging easier
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            movieJsonStr = buffer.toString();

        } catch (IOException e) {
            Log.e("MainActivityFragment", "Error ", e);

            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("MainActivityFragment", "Error closing stream", e);
                }
            }
        }
        Log.i("sort", movieJsonStr);
        return movieJsonStr;
    }

    @Override
    protected void onPostExecute(String movieJsonStr ){
        try {
            ArrayList<MovieInfo> moviesObj = getMovieDataFromJson(movieJsonStr);
            if(moviesObj !=null) {
                Log.i("sort onPost", movieJsonStr);
                MainActivityFragment.setMovieAdapter(moviesObj);
            }
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }
}
