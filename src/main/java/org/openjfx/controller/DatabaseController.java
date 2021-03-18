package org.openjfx.controller;

import org.openjfx.App;
import org.openjfx.model.Genre;
import org.openjfx.model.Movie;
import org.openjfx.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * THIS CLASS IS FOR THE DATA RETRIEVING FROM OUR OWN DATABASE SQLITE
 * @author Dang Ngan Ha, Ngo Quoc Thai, Nguyen Phan Yen Ngan
 */
public class DatabaseController {

    /**
     * CONNECT TO DATABASE
     */
    private static Connection connect() {
        String path = "jdbc:sqlite:src/main/java/org/openjfx/model/data.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(path);
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * INSERT THE USER'S PREFERABLE TYPES OF GENRES FOR PERSONALIZATION INTO TABLE 'USERS_GENRE'
     */
    public void insertGenreIDToTableUSERS_GENRE(List<Genre> genres) {
        Connection conn = connect();
        // TODO

        for (Genre each: genres) {
            String sqlQuery = "INSERT INTO USERS_GENRE('username', 'genre', 'id_genre') VALUES (?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery)){
                pstmt.setString(1, App.username);
                pstmt.setString(2, each.getName());
                pstmt.setString(3, each.getId());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * INSERT INTO TABLE 'USERS_WISHLISTS' THE MOVIES WHICH USER WISHES TO WATCH
     */
    public void insertMovieToTableUSERS_WISHLISTS(String movie_id) {
        String query = "INSERT INTO USERS_WISHLISTS('username', 'movie_id') VALUES (?, ?)";
        Connection conn = this.connect();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, App.username);
            pstmt.setString(2, movie_id);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * INSERT USER'S PERSONAL INFORMATION INTO TABLE 'USERS': USERNAME, NAME, PASSWORD
     */
    public void insertUserToTableUSERS(User user) {
        //
        String query = "INSERT INTO USERS('username','password','firstname', 'lastname') VALUES(?, ?, ?, ?)";
        Connection conn = this.connect();
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getFirstName());
            pstmt.setString(4, user.getLastName());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    /**
     * INSERT USER'S PERSONAL MOVIE RATINGS INTO TABLE 'USERS_MOVIE'
     */
    public void insertRatedMovieFromTableUSERS_MOVIE(String movie_id, String choice) {
        String query = "INSERT INTO USERS_MOVIE('username', 'movie_id', 'rated') VALUES (?, ?, ?)";
        Connection conn = this.connect();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, App.username);
            pstmt.setString(2, movie_id);
            pstmt.setString(3, choice);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * RETRIEVING USER'S PERSONAL DATA TO DISPLAY ON THE APP
     */
    public User getUserInfoFromTableUSERS(String username){
        String query = "SELECT * FROM USERS where username = ?";
        Connection conn = this.connect();
        User user = null;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet results = pstmt.executeQuery();
            user = new User(results.getString("username"), results.getString("password"), results.getString("firstname"), results.getString("lastname"));
            conn.close();
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
        return user;
    }

    /**
     * RETRIEVING USER'S PASSWORD FOR THE LOG IN PROCESS
     */
    public boolean getPasswordFromTableUSERS(User user){
        String query = "SELECT password FROM USERS where username = ?";
        Connection conn = this.connect();
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, user.getUsername());
            ResultSet results = pstmt.executeQuery();
            if (user.getPassword().equals(results.getString("password"))) {
                conn.close();
                return true;
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * GET THE MOVIE IDS THAT USER HAS CHOSEN WHEN REGISTER
     * THOSE ARE FOR RETRIEVING THE MOVIES WITH THE SIMILAR TYPES THAT USER HAS INITIALLY CHOSEN
     */
    public List<Genre> getChosenGenreIDFromTableUSERS_GENRE(String username) {
        List<Genre> results = new ArrayList<>();

        String query = "SELECT * FROM USERS_GENRE WHERE username = ?";
        Connection conn = this.connect();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                results.add(new Genre(resultSet.getString("genre"), resultSet.getString("id_genre")));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * RETRIEVING THE MOVIES IN USER'S WISH LIST TO DISPLAY OUR RECOMMENDATIONS
     * ON THE TAB 'RECOMMENDED MOVIES' IN THE APP
     */
    public List<String> getMovieIDFromTableUSERS_WISHLISTS() {
        List<String> movies_ids = new ArrayList<>();

        String query = "SELECT movie_id from USERS_WISHLISTS WHERE username = ?";
        Connection conn = this.connect();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, App.username);
            ResultSet results = pstmt.executeQuery();

            while (results.next()) {
                movies_ids.add(results.getString("movie_id"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies_ids;
    }

    /**
     * RETRIEVING THE RANDOM MOVIES PICK FROM USER'S RATED MOVIES
     */
    public String getRandomRatedMovieIdFromTableUSERS_MOVIE() {
        String query = "SELECT movie_id from USERS_MOVIE WHERE username = ?";
        Connection conn = this.connect();
        List<String> movieIds = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, App.username);
            ResultSet results = pstmt.executeQuery();

            while (results.next()) {
                movieIds.add(results.getString("movie_id"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (movieIds.isEmpty()) {
            return "";
        }
        return movieIds.get(new Random().nextInt(movieIds.size()));
    }

    /**
     * GET THE MOVIES IN THE TABLE 'USERS_MOVIE'
     */
    public List<String> getMovieIDsFromUSERS_MOVIE() {
        List<String> movies_ids = new ArrayList<>();

        String query = "SELECT movie_id from USERS_MOVIE WHERE username = ?";
        Connection conn = this.connect();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, App.username);
            ResultSet results = pstmt.executeQuery();

            while (results.next()) {
                movies_ids.add(results.getString("movie_id"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies_ids;
    }

    /**
     * UPDATE USER PROFILE FUNCTION
     */
    public void updateProfileFromTableUSERS(User user) {
        String query = "UPDATE USERS SET password = ?, firstname = ?, lastname = ? WHERE username = ?";
        Connection conn = this.connect();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getFirstName());
            pstmt.setString(3, user.getLastName());
            pstmt.setString(4, user.getUsername());

            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * UPDATE THE USER'S RATING WHEN THEY WANT TO RATE A MOVIE MORE THAN 1 TIME
     */
    public void updateRatedMovieFromTableUSERS_Movie(String movie_id, String choice) {
        String query = "UPDATE USERS_MOVIE SET rated = ? WHERE username = ? AND movie_id = ?";
        Connection conn = this.connect();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, choice);
            pstmt.setString(2, App.username);
            pstmt.setString(3, movie_id);

            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * CHECK IF USER ACCOUNT EXISTS IN DATABASE
     */
    public boolean checkIfExistAccountFromTableUSERS(User user) {
        String query = "SELECT * FROM USERS where username = ?";
        Connection conn = this.connect();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, user.getUsername());
            ResultSet results = pstmt.executeQuery();
            conn.close();
            if (results.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
        return false;
    }

    /**
     * CHECK IF THERE EXISTS ANY MOVIES THAT USER HAS PREVIOUSLY ADD TO THEIR WISH LIST
     */
    public boolean checkIfMovieIDIsAlreadyAddedFromTableUSERS_WISHLISTS(String movie_id) {
        String query = "SELECT * FROM USERS_WISHLISTS where username = ? AND movie_id = ?";
        Connection conn = this.connect();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, App.username);
            pstmt.setString(2, movie_id);
            ResultSet results = pstmt.executeQuery();
            conn.close();
            if (results.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
        return false;
    }

    /**
     * CHECK IF THERE EXISTS ANY MOVIES THAT USER HAS PREVIOUSLY RATED
     */
    public boolean checkIfMovieIsRatedFromTableUSERS_MOVIE(String movie_id) {
        String query = "SELECT * FROM USERS_MOVIE where username = ? AND movie_id = ?";
        Connection conn = this.connect();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, App.username);
            pstmt.setString(2, movie_id);
            ResultSet results = pstmt.executeQuery();
            conn.close();

            if (results.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
        return false;
    }

    /**
     * CHECK IF USER HAS RATED ANY MOVIE BEFORE
     */
    public boolean checkIfUserHasRatedAnyMovie() {
        String query = "SELECT * FROM USERS_MOVIE WHERE username = ?";
        Connection conn = this.connect();

        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, App.username);

            ResultSet results = pstmt.executeQuery();
            conn.close();

            if (results.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
        return false;
    }
}
