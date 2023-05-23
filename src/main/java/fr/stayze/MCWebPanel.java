package fr.stayze;

import fi.iki.elonen.NanoHTTPD;
import fr.stayze.database.Database;
import fr.stayze.database.EntityManager;
import fr.stayze.database.tables.USER;
import fr.stayze.web.WebServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MCWebPanel extends JavaPlugin {

    private static MCWebPanel instance;

    private WebServer server;

    @Override
    public void onEnable() {
        try {
            this.init();
            System.out.println("Plugin loaded ! okayyyyy");
        } catch (IOException | SQLException | ClassNotFoundException e) {
            //System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void init() throws IOException, SQLException, ClassNotFoundException {
        instance = this;
        this.server = new WebServer(8080);
        Database.connect();
        this.server.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        EntityManager entityManager = new EntityManager(Database.getConnection());
        List<USER> users = entityManager.getAll(USER.class);
        System.out.println(users.get(0).getEMAIL());
    }

    @Override
    public void onDisable() {
        try {

            this.server.stop();
            Database.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static MCWebPanel getInstance() {
        return instance;
    }
    
}
