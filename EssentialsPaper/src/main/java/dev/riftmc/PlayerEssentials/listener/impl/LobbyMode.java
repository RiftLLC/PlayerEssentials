package dev.riftmc.PlayerEssentials.listener.impl;

import dev.riftmc.PlayerEssentials.Essentials;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LobbyMode implements Listener {

    private final boolean enabled;
    private final Essentials plugin;

    public LobbyMode(Essentials plugin) {
        enabled = plugin.serverSettings.isLobbyMode();
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

    }

}
