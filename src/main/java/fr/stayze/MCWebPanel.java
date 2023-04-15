package fr.stayze;

import fr.stayze.web.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class MCWebPanel extends JavaPlugin {

    private Server server;

    @Override
    public void onEnable() {
        try {
            this.init();
            System.out.println("Plugin loaded ! okayyyyy");
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private void init() throws IOException {

        this.server = new Server(8080);
        System.out.println("OK");

    }

    @Override
    public void onDisable() {
        this.server.stop();
    }
}
