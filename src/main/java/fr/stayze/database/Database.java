package fr.stayze.database;

import fr.stayze.MCWebPanel;
import fr.stayze.utils.ResourceLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static Connection database;

    public Database() throws ClassNotFoundException, SQLException {
        database = null;
        Class.forName("org.sqlite.JDBC");
        database = DriverManager.getConnection("jdbc:sqlite:" + MCWebPanel.getInstance().getDataFolder() + "\\database.sql");
        System.out.println("Opened database successfully");
        init();
    }

    public void init() throws SQLException {
        String sql = ResourceLoader.loadFile("database.sql");
        Statement statement = getConnection().createStatement();
        statement.executeUpdate(sql);
    }

    public static void connect() throws SQLException, ClassNotFoundException { new Database(); }
    public static void close() throws SQLException { database.close(); }
    public static Connection getConnection() { return database; }

}
