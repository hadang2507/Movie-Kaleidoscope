package org.openjfx.controller;

import org.openjfx.App;
import org.openjfx.model.Genre;
import org.openjfx.model.Movie;
import org.openjfx.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {

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
}
