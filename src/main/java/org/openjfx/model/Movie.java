package org.openjfx.model;

import java.util.List;

/*
MOVIE DETAIL
 */
public class Movie {
    private String id;
    private String budget;
    private String adult;
    private String backdrop_path;
    private String belongs_to_collection;
    private List<Integer> genre_ids;
    private String homepage;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private String popularity;
    private String poster_path;
    private List<ProductionCompany> production_companies;
    private String status;
    private String tagline;
    private String title;
    private String video;
    private String vote_average;
    private String vote_count;



    public Movie(String id,
                 String budget,
                 String adult,
                 String backdrop_path,
                 String belongs_to_collection,
                 List<Integer> genre_ids,
                 String homepage,
                 String imdb_id,
                 String original_language,
                 String original_title,
                 String overview,
                 String popularity,
                 String poster_path,
                 List<ProductionCompany> production_companies,
                 String status,
                 String tagline,
                 String title,
                 String video,
                 String vote_average,
                 String vote_count
    ){
        this.id = id;
        this.budget = budget;
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.belongs_to_collection = belongs_to_collection;
        this.genre_ids = genre_ids;
        this.homepage = homepage;
        this.imdb_id = imdb_id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.production_companies = production_companies;
        this.status = status;
        this.tagline = tagline;
        this.title =  title;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public String getId() {
        return id;
    }

    public List<Integer> getGenres() {
        return genre_ids;
    }

    public String getAdult() {
        return adult;
    }

    public List<ProductionCompany> getProduction_companies() {
        return production_companies;
    }

    public String getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public String getBudget() {
        return budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPopularity() {
        return popularity;
    }
    public String getPoster_path() {
        return "https://image.tmdb.org/t/p/w500/" + poster_path;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }

    public String getVideo() {
        return video;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getVote_count() {
        return vote_count;
    }
}
