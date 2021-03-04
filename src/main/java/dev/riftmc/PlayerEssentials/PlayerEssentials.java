package dev.riftmc.PlayerEssentials;

import dev.riftmc.PlayerEssentials.tools.ServerSettings;
import dev.riftmc.PlayerEssentials.tools.VersionChecker;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

// Player Essentials by Quizaciously
public class PlayerEssentials extends JavaPlugin {

    public VersionChecker versionChecker;
    public ServerSettings serverSettings;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();

        versionChecker = new VersionChecker(this);
        serverSettings = new ServerSettings(this);
    }

    @Override
    public void onDisable() {
        versionChecker.remind();
    }

}
