package dev.riftmc.PlayerEssentials.listener;

import dev.riftmc.PlayerEssentials.Essentials;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static dev.riftmc.PlayerEssentials.tools.StringManager.cc;

public class ConnectionListener implements Listener {

    private final Essentials plugin;
    public ConnectionListener(Essentials plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onConnect(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        //TODO: placeholder api
        event.joinMessage(cc(plugin.serverSettings.getJoinMessage()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        //TODO: placeholder api
        event.quitMessage(cc(plugin.serverSettings.getQuitMessage()));
    }

}
