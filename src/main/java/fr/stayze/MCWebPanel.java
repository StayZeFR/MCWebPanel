package fr.stayze;

import fi.iki.elonen.NanoHTTPD;
import fr.stayze.database.Database;
import fr.stayze.utils.Session;
import fr.stayze.web.WebServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.sql.SQLException;

public class MCWebPanel extends JavaPlugin {

    private static MCWebPanel instance;

    private WebServer server;
    private Database database;

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
        this.database = new Database();
        this.server.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
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
