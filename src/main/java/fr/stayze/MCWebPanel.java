package fr.stayze;

import fr.stayze.system.App;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class MCWebPanel extends JavaPlugin {

    private App app;

    @Override
    public void onEnable() {

        try {
            this.app = new App();
            this.app.start();
        } catch (InstantiationException | IllegalAccessException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onDisable() {
        this.app.stop();
    }
}
