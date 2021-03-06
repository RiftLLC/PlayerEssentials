package dev.riftmc.PlayerEssentials.listener.impl;

import dev.riftmc.PlayerEssentials.Essentials;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.logging.Logger;

public class LobbyMode implements Listener {

    private static final Logger logger = Logger.getLogger("Minecraft");

    private final boolean enabled;
    private final Essentials plugin;

    public LobbyMode(Essentials plugin) {
        enabled = plugin.serverSettings.isLobbyMode();
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (enabled) {
            // do lobby stuff
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (enabled) {
            // do lobby stuff
        }
    }

}
