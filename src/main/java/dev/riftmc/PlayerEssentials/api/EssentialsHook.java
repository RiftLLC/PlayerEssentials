package dev.riftmc.PlayerEssentials.api;

import dev.riftmc.PlayerEssentials.PlayerEssentials;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/* The Essentials API */
public class EssentialsHook {

    private final PlayerEssentials essentials;

    public EssentialsHook() {
        this.essentials = JavaPlugin.getPlugin(PlayerEssentials.class);
    }

    //TODO: expand api
    public PlayerEssentials getEssentials() {
        return essentials;
    }

}
