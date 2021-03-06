package dev.riftmc.PlayerEssentials.api;

import dev.riftmc.PlayerEssentials.Essentials;

import java.util.logging.Logger;

public class PlaceholderHook {

    private final Logger logger = Logger.getLogger("Minecraft");
    private final Essentials plugin;

    public PlaceholderHook(Essentials plugin) {
        this.plugin = plugin;
        if (hasPlaceholderAPI())
            logger.info("[PlaceholderHook] Successfully found PlaceholderAPI!");
        else
            logger.info("[PlaceholderHook] Could not find PlaceholderAPI on the server, please download it before your next launch!");
    }

    public boolean hasPlaceholderAPI() {
        return plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null;
    }

    public void remind() {
        if (!hasPlaceholderAPI())
            logger.info("[PlaceholderHook] Could not find PlaceholderAPI on the server, please download it before your next launch!");
    }

}
