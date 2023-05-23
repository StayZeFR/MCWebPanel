package fr.stayze.web.pages;

import fi.iki.elonen.NanoHTTPD;
import fr.stayze.database.Database;
import fr.stayze.utils.ResourceLoader;
import fr.stayze.utils.Utils;

import java.sql.SQLException;
import java.util.Map;

public class IndexPage extends Page {

    public IndexPage(NanoHTTPD.IHTTPSession session) {
        super(session);
        //Map<String, String> user = Utils.getUserByToken(this.session);
        /*this.data.put("NB_PLAYERS", String.valueOf(Bukkit.getOnlinePlayers().size()));
        this.data.put("MAX_PLAYERS", String.valueOf(Bukkit.getMaxPlayers()));
        Runtime runtime = Runtime.getRuntime();
        this.subData.put("NB_RAM", String.valueOf(Math.round((runtime.totalMemory() - runtime.freeMemory()) / 1048576.0)));
        this.subData.put("MAX_RAM", String.valueOf(Math.round(runtime.maxMemory() / 1048576.0)));*/
        //this.subData.put("USER", user.get("USERNAME"));
        this.data.put("MAIN", ResourceLoader.loadFile("html/templates/index.html"));
    }

    @Override
    protected void POST() {

        try {
            Map<String, String> data = this.session.getParms();
            if (data.get("logout").equalsIgnoreCase("true")) {
                Map<String, String> cookies = Utils.parseCookie(session.getHeaders().get("cookie"));
                Database.session().delete("TOKEN", cookies.get("TOKEN"));
                this.redirect("/login");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
