package dev.riftmc.PlayerEssentials.tools;

import dev.riftmc.PlayerEssentials.Essentials;

import static dev.riftmc.PlayerEssentials.tools.StringManager.combine;

public class ServerSettings {

    /* Setting Names */
    private final String name, joinMessage, quitMessage, motd;
    private final boolean motdEnabled;

    /* Setting the Settings */
    public ServerSettings(Essentials plugin) {
        /* Run on Startup */
        name = plugin.getConfig().getString("server-name");
        joinMessage = plugin.messagesFile.getConfig().getString("join-message");
        quitMessage = plugin.messagesFile.getConfig().getString("leave-message");
        motd = combine(plugin.messagesFile.getConfig().getStringList("motd.lines"), "\n");

        motdEnabled = plugin.messagesFile.getConfig().getBoolean("motd.enabled");
    }

    /* Getting the Settings */
    public String getName() {
        return name;
    }

    public String getJoinMessage() {
        return joinMessage;
    }

    public String getQuitMessage() {
        return quitMessage;
    }

    public String getMotd() {
        return motd;
    }

    public boolean isMotdEnabled() {
        return motdEnabled;
    }

}
