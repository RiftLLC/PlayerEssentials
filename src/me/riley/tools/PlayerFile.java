package me.riley.tools;

import me.riley.HeroicEssentials;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayerFile {

    private File file;
    private FileConfiguration config;
    private HeroicEssentials plugin;

    public PlayerFile() {
        plugin = HeroicEssentials.getEssentials();
        file = new File(plugin.getDataFolder(), "players.yml");
        if (!file.exists()) {
            file.getParentFile().mkdir();
            plugin.saveResource("players.yml", false);
        }

        config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        try {
            config.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
