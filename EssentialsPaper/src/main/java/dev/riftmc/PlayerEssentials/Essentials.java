package dev.riftmc.PlayerEssentials;

import dev.riftmc.PlayerEssentials.api.PlaceholderHook;
import dev.riftmc.PlayerEssentials.api.VaultHook;
import dev.riftmc.PlayerEssentials.listener.ListenerManager;
import dev.riftmc.PlayerEssentials.tools.ConfigFile;
import dev.riftmc.PlayerEssentials.tools.ServerSettings;
import dev.riftmc.PlayerEssentials.tools.VersionChecker;
import org.bukkit.plugin.java.JavaPlugin;

// Player Essentials by Quizaciously
public class Essentials extends JavaPlugin {

    /* Tools */
    public VersionChecker versionChecker;
    public PlaceholderHook placeholderHook;
    public ServerSettings serverSettings;
    public ConfigFile messagesFile;

    /* Dependencies */
    public VaultHook vaultHook;
    /* Commands & Events */
    private ListenerManager listenerManager;

    @Override
    public void onEnable() {
        checkServerJar();
        messagesFile = new ConfigFile(this, "messages.yml");
        getConfig().options().copyDefaults(true);
        saveConfig();

        vaultHook = new VaultHook(this);
        placeholderHook = new PlaceholderHook(this);
        listenerManager = new ListenerManager(this);

        versionChecker = new VersionChecker(this);
        serverSettings = new ServerSettings(this);
    }

    @Override
    public void onDisable() {
        versionChecker.remind();
        placeholderHook.remind();
        vaultHook.remind();
    }

    private void checkServerJar() {
        try {
            Class.forName("com.destroystokyo.paper.VersionHistoryManager$VersionData");
            getLogger().info("Successfully found PaperSpigot!");
        } catch (ClassNotFoundException e) {
            getLogger().info("Could not find PaperSpigot, disabling!");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

}
