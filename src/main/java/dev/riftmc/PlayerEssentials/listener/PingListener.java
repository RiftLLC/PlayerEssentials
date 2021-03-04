package dev.riftmc.PlayerEssentials.listener;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import dev.riftmc.PlayerEssentials.Essentials;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static dev.riftmc.PlayerEssentials.tools.StringManager.cc;

public class PingListener implements Listener {

    private final Essentials plugin;
    public PingListener(Essentials plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPing(PaperServerListPingEvent event) {
        //TODO: placeholder api
        if (plugin.serverSettings.isMotdEnabled()) {
            event.motd(cc(plugin.serverSettings.getMotd()));
        }
    }

}
