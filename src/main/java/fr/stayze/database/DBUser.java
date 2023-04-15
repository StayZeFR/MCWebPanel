package fr.stayze.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUser {

    public static boolean isUserHaveSession(int id, String token) throws SQLException {
        String sql = "SELECT * FROM SESSION WHERE ID_USER = ? AND TOKEN = ?;";
        PreparedStatement query = Database.getConnection().prepareStatement(sql);
        query.setInt(1, id);
        query.setString(2, token);
        ResultSet result = query.executeQuery();
        int count = 0;
        while (result.next()) count++;
        return count > 0;
    }

    public static void insertToken(int id, String token) throws SQLException {
        String sql = "SELECT * FROM SESSION WHERE ID_USER = ?;";
        PreparedStatement query = Database.getConnection().prepareStatement(sql);
        query.setInt(1, id);
        ResultSet result = query.executeQuery();
        int count = 0;
        while (result.next()) count++;

        String sql2;
        if (count > 0) sql2 = "UPDATE SESSION SET TOKEN = ? WHERE ID_USER = ?;";
        else sql2 = "INSERT INTO SESSION (TOKEN, ID_USER) VALUES (?, ?);";
        PreparedStatement query2 = Database.getConnection().prepareStatement(sql2);
        query2.setInt(1, id);
        query2.setString(2, token);
        query2.execute();
    }

}
