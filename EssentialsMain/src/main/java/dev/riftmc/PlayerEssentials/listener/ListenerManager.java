package dev.riftmc.PlayerEssentials.listener;

import dev.riftmc.PlayerEssentials.Essentials;
import dev.riftmc.PlayerEssentials.listener.impl.ConnectionListener;
import dev.riftmc.PlayerEssentials.listener.impl.PingListener;
import org.bukkit.event.Listener;

import java.util.logging.Logger;

public class ListenerManager {

    private final Logger logger = Logger.getLogger("Minecraft");
    private final Essentials plugin;
    private int amountOfListeners = 0;

    public ListenerManager(Essentials plugin) {
        this.plugin = plugin;
        logger.info("Registering events...");
        registerStartupListeners();
        logger.info("Successfully registered " + amountOfListeners + " events!");
    }

    private void registerStartupListeners() {
        register(new ConnectionListener(plugin));
        register(new PingListener(plugin));
    }

    public void register(Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        amountOfListeners++;
    }

}
