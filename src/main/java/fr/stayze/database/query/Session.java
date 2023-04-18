package fr.stayze.database.query;

import fr.stayze.database.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Session extends Query {

    public Session() {
        this.table = "SESSION";
    }

    public void insert(int id, String token) throws SQLException {
        String sql = "INSERT INTO SESSION (ID_USER, TOKEN) VALUES (?, ?);";
        PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.setString(2, token);
        pstmt.execute();
    }

}
