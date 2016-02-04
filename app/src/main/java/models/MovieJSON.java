package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Al on 1/28/2016.
 */


public class MovieJSON {

    public String posterPath;
    public Boolean adult;
    public String overview;
    public String releaseDate;
    public List<Integer> genreIds = new ArrayList<Integer>();
    public Integer id;
    public String originalTitle;
    public String originalLanguage;
    public String title;
    public String backdropPath;
    public Float popularity;
    public Integer voteCount;
    public Boolean video;
    public Float voteAverage;

}
