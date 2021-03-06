package dev.riftmc.PlayerEssentials.tools;

import dev.riftmc.PlayerEssentials.Essentials;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFile {

    private final File file;
    private final FileConfiguration config;

    public ConfigFile(Essentials plugin, String name) {
        file = new File(plugin.getDataFolder(), name);
        if (!file.exists()) {
            file.getParentFile().mkdir();
            plugin.saveResource(name, false);
        }

        config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
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
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}