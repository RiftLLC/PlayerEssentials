package dev.riftmc.PlayerEssentials.listener;

import dev.riftmc.PlayerEssentials.PlayerEssentials;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static dev.riftmc.PlayerEssentials.tools.ColoredMessage.cc;

public class ConnectionListener implements Listener {

    private final PlayerEssentials plugin;
    public ConnectionListener(PlayerEssentials plugin) {
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
