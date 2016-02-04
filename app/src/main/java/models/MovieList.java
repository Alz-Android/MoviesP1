package models;

import java.util.ArrayList;
import java.util.List;

public class MovieList {

    public Integer page;
    public List<MovieJSON> results = new ArrayList<MovieJSON>();
    public Integer totalResults;
    public Integer totalPages;

}