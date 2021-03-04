package dev.riftmc.PlayerEssentials.tools;

import dev.riftmc.PlayerEssentials.PlayerEssentials;
import org.bukkit.entity.Player;

public class ServerSettings {

    /* Setting Names*/
    private final String name, joinMessage, quitMessage;

    /* Setting the Settings */
    public ServerSettings(PlayerEssentials plugin) {
        /* Run on Startup */
        name = plugin.getConfig().getString("server-name");
        joinMessage = plugin.getConfig().getString("join-message");
        quitMessage = plugin.getConfig().getString("leave-message");
    }

    /* Getting the Settings */
    public String getName() {
        return name;
    }
    public String getJoinMessage() { return joinMessage; }
    public String getQuitMessage() { return quitMessage; }

}
