package org.openjfx.controller;

import org.openjfx.App;
import org.openjfx.model.Genre;
import org.openjfx.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            } catch (SQLException e) {
                System.out.println("error: " + e.getMessage());
                e.printStackTrace();
            }
        }



    }

    public boolean checkIfExistAccount(User user) {

        Connection conn = connect();
        return false;
    }

    public void insertUserToTableUSERS(User user) {
        // TODO
    }
}
