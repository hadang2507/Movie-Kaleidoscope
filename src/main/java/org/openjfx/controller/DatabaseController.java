package org.openjfx.controller;

import org.openjfx.App;
import org.openjfx.model.Genre;
import org.openjfx.model.Movie;
import org.openjfx.model.User;

import java.sql.*;
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
        System.out.println(conn);

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

    public boolean checkIfExistAccount(User user) {
        //
        String query = "SELECT * FROM USERS where username = ?";
        Connection conn = this.connect();
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, user.getUsername());
            ResultSet results = pstmt.executeQuery();
            conn.close();
            if (results != null) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
        return false;
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
}
