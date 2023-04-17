package fr.stayze.utils;

import fr.stayze.database.Database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class User {

    public static Map<String, String> checkUser(String username, String password) throws SQLException {
        Map<String, String> data = new HashMap<>();
        String sql = "SELECT * FROM USER WHERE USERNAME = ? OR EMAIL = ? AND PASSWORD = ?";
        PreparedStatement query = Database.getConnection().prepareStatement(sql);
        query.setString(1, username);
        query.setString(2, username);
        query.setString(3, password);
        ResultSet result = query.executeQuery();
        ResultSetMetaData metaData = result.getMetaData();
        int columnCount = metaData.getColumnCount();
        if (result.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                String value = result.getString(columnName);
                data.put(columnName, value);
            }
        }
        return data;
    }

}
