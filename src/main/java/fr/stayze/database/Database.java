package fr.stayze.database;

import fr.stayze.MCWebPanel;
import fr.stayze.database.query.Session;
import fr.stayze.database.query.User;
import fr.stayze.utils.ResourceLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static Connection database;
    private static Session session;
    private static User user;

    public Database() throws ClassNotFoundException, SQLException {
        database = null;
        Class.forName("org.sqlite.JDBC");
        database = DriverManager.getConnection("jdbc:sqlite:" + MCWebPanel.getInstance().getDataFolder() + "\\database.sql?busy_timeout=5000");
        System.out.println("Opened database successfully");
        init();
    }

    public void init() throws SQLException {
        String sql = ResourceLoader.loadFile("database.sql");
        Statement statement = getConnection().createStatement();
        statement.executeUpdate(sql);

        session = new Session();
        user = new User();
    }

    public static Session session() { return session; }
    public static User user() { return user; }

    public static void connect() throws SQLException, ClassNotFoundException { new Database(); }
    public static void close() throws SQLException { database.close(); }
    public static Connection getConnection() { return database; }

}
