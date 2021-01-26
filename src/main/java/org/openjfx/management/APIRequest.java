package org.openjfx.management;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.openjfx.App;
import org.openjfx.controller.DatabaseController;
import org.openjfx.controller.SearchController;
import org.openjfx.model.Genre;
import org.openjfx.model.Movie;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class APIRequest {

    // get Json String from URL
    private String getJsonFromURL(String urlStringFormat) throws IOException, JsonParseException {
        URL url = new URL(urlStringFormat);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        if (!responseStatusTrueOrFalse(conn.getResponseCode())) {
            return "The query is not successfully executed.";
        }

        return this.returnStringFromJson(url);
    }

    private String returnStringFromJson(URL url) throws IOException {
        String value = "";
        Scanner scan = new Scanner(url.openStream());

        while (scan.hasNext()) {
            value += scan.nextLine();
        }

        return value;
    }

    private boolean responseStatusTrueOrFalse(int respondCode) {
        return respondCode >= 200;
    }


    public List<Genre> getGenreAndId() {
        String url = "https://api.themoviedb.org/3/genre/movie/list?api_key=405b7ef5e944fb61f960538017e4d88b&language=en-US";

        try {
            String jsonStringInline = getJsonFromURL(url);

            Gson g = new Gson();
            JsonElement element = g.fromJson(jsonStringInline, JsonElement.class);
            JsonObject value = element.getAsJsonObject();

            JsonArray genres = value.getAsJsonArray("genres");

            Type genreListType = new TypeToken<ArrayList<Genre>>() {
            }.getType();
            List<Genre> genresList = g.fromJson(genres, genreListType);

            return genresList;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Movie> getMoviesFromSearch(String search) {
        List<Movie> movies = new ArrayList<>();

        return movies;
    }

    public List<Movie> getMoviesFromGenres() {
        DatabaseController dbController = new DatabaseController();
        List<Genre> genres = dbController.getChosenGenreIDFromTableUSERS_GENRE(App.username);

        String url = "https://api.themoviedb.org/3/discover/movie?api_key=405b7ef5e944fb61f960538017e4d88b&language=en-US&sort_by=popularity.desc&include_video=false&page=1&with_genres=" + genres.get(0).getName();
        try {
            String jsonStringInline = getJsonFromURL(url);
            //System.out.println(jsonStringInline);

            Gson g = new Gson();
            JsonElement element = g.fromJson(jsonStringInline, JsonElement.class);
            JsonObject value = element.getAsJsonObject();
            JsonArray moviesList = value.getAsJsonArray("results");

            Type movieListType = new TypeToken<ArrayList<Movie>>(){}.getType();
            List<Movie> movies = g.fromJson(moviesList, movieListType);
            return movies;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Movie> getTopRatedMovies(){
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=405b7ef5e944fb61f960538017e4d88b&language=en-US&page=1";
        //String url2_page2 = "https://api.themoviedb.org/3/movie/top_rated?api_key=405b7ef5e944fb61f960538017e4d88b&language=en-US&page=2";
        try {
            String jsonStringInline = getJsonFromURL(url);
            System.out.println(jsonStringInline);

            Gson g = new Gson();
            JsonElement element = g.fromJson(jsonStringInline, JsonElement.class);
            JsonObject value = element.getAsJsonObject();
            JsonArray moviesList = value.getAsJsonArray("results");

            Type movieListType = new TypeToken<ArrayList<Movie>>(){}.getType();
            List<Movie> movies = g.fromJson(moviesList, movieListType);


            return movies;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Movie> getSearchedMovies(String searchText) {
        searchText = searchText.replace(" ", "%20");
        String url = "https://api.themoviedb.org/3/search/multi?api_key=405b7ef5e944fb61f960538017e4d88b&language=en-US&page=1&include_adult=true&query=" + searchText;
        System.out.println(url);
        try {
            String jsonStringInline = getJsonFromURL(url);
            System.out.println(jsonStringInline);

            Gson g = new Gson();
            JsonElement element = g.fromJson(jsonStringInline, JsonElement.class);
            JsonObject value = element.getAsJsonObject();
            JsonArray moviesList = value.getAsJsonArray("results");

            Type movieListType = new TypeToken<ArrayList<Movie>>() {
            }.getType();
            List<Movie> movies = g.fromJson(moviesList, movieListType);


            return movies;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Movie> getRecommendedMoviesByMovieId(String movieId){

        String url = "https://api.themoviedb.org/3/movie/"+ movieId +"/recommendations?api_key=405b7ef5e944fb61f960538017e4d88b&language=en-US&page=1";
        System.out.println(url);
        try {
            String jsonStringInline = getJsonFromURL(url);
            System.out.println(jsonStringInline);

            Gson g = new Gson();
            JsonElement element = g.fromJson(jsonStringInline, JsonElement.class);
            JsonObject value = element.getAsJsonObject();
            JsonArray moviesList = value.getAsJsonArray("results");

            Type movieListType = new TypeToken<ArrayList<Movie>>() {}.getType();
            List<Movie> movies = g.fromJson(moviesList, movieListType);


            return movies;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Movie getMovieByMovieId (String movieId){
        String url = "https://api.themoviedb.org/3/movie/"+movieId+"?api_key=405b7ef5e944fb61f960538017e4d88b&language=en-US";
        System.out.println(url);
        try {
            String jsonStringInline = getJsonFromURL(url);
            System.out.println(jsonStringInline);

            Gson g = new Gson();
            JsonElement element = g.fromJson(jsonStringInline, JsonElement.class);
            JsonObject value = element.getAsJsonObject();
            Type movieType = new TypeToken<Movie>() {}.getType();
            Movie movie = g.fromJson(value, movieType);
            System.out.println(movie);
            return movie;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
