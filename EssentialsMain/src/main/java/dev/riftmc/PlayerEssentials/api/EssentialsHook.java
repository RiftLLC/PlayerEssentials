package dev.riftmc.PlayerEssentials.api;

import dev.riftmc.PlayerEssentials.Essentials;
import org.bukkit.plugin.java.JavaPlugin;

/* The Essentials API */
public class EssentialsHook {

    private final Essentials essentials;

    public EssentialsHook() {
        this.essentials = JavaPlugin.getPlugin(Essentials.class);
    }

    //TODO: expand api
    public Essentials getEssentials() {
        return essentials;
    }

    public VaultHook getVaultHook() {
        return getEssentials().vaultHook;
    }

    public String getServerName() {
        return getEssentials().serverSettings.getName();
    }

}
