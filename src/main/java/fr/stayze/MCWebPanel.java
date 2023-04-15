package fr.stayze;

import fr.stayze.database.Database;
import fr.stayze.web.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.sql.SQLException;

public class MCWebPanel extends JavaPlugin {

    private static MCWebPanel instance;

    private Server server;

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
        this.server = new Server(8080);
        new Database();
    }

    @Override
    public void onDisable() {
        this.server.stop();
    }

    public static MCWebPanel getInstance() {
        return instance;
    }
    
}
