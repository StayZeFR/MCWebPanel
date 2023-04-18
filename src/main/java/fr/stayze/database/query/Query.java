package fr.stayze.database.query;

import fr.stayze.database.Database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Query {

    protected Connection database;
    protected String table;

    public Query() {
        this.database = Database.getConnection();
    }

    public Map<String, String> find(String column, String value) throws SQLException {
        Map<String, String> result = new HashMap<>();
        String sql = "SELECT * FROM " + this.table + " WHERE " + column + " = ?;";
        PreparedStatement pstmt = this.database.prepareStatement(sql);
        pstmt.setString(1, value);
        ResultSet data = pstmt.executeQuery();
        ResultSetMetaData metaData = data.getMetaData();
        int columnCount = metaData.getColumnCount();
        if (data.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String c = metaData.getColumnName(i);
                String v = data.getString(c);
                result.put(c, v);
            }
        }
        return result;
    }

    public Map<String, String> find(String column, int value) throws SQLException {
        Map<String, String> result = new HashMap<>();
        String sql = "SELECT * FROM " + this.table + " WHERE " + column + " = ?;";
        PreparedStatement pstmt = this.database.prepareStatement(sql);
        pstmt.setInt(1, value);
        ResultSet data = pstmt.executeQuery();
        ResultSetMetaData metaData = data.getMetaData();
        int columnCount = metaData.getColumnCount();
        if (data.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String c = metaData.getColumnName(i);
                String v = data.getString(c);
                result.put(c, v);
            }
        }
        return result;
    }

    public void delete(String column, String value) throws SQLException {
        String sql = "DELETE FROM " + this.table + " WHERE " + column + " = ?;";
        PreparedStatement pstmt = this.database.prepareStatement(sql);
        pstmt.setString(1, value);
        pstmt.execute();
    }

    public void delete(String column, int value) throws SQLException {
        String sql = "DELETE FROM " + this.table + " WHERE " + column + " = ?;";
        PreparedStatement pstmt = this.database.prepareStatement(sql);
        pstmt.setInt(1, value);
        pstmt.execute();
    }

}
