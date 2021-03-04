package dev.riftmc.PlayerEssentials;

import dev.riftmc.PlayerEssentials.api.VaultHook;
import dev.riftmc.PlayerEssentials.tools.ConfigFile;
import dev.riftmc.PlayerEssentials.tools.ServerSettings;
import dev.riftmc.PlayerEssentials.tools.VersionChecker;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

// Player Essentials by Quizaciously
public class PlayerEssentials extends JavaPlugin {

    /* Player Essentials */
    public VersionChecker versionChecker;
    public ServerSettings serverSettings;
    public ConfigFile messagesFile;

    /* Dependencies */
    public VaultHook vaultHook;

    @Override
    public void onEnable() {
        messagesFile = new ConfigFile(this, "messages.yml");
        getConfig().options().copyDefaults(true);
        saveConfig();

        vaultHook = new VaultHook(this);

        versionChecker = new VersionChecker(this);
        serverSettings = new ServerSettings(this);
    }

    @Override
    public void onDisable() {
        versionChecker.remind();

        vaultHook.remind();
    }

}
