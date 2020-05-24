package me.riley.tools;

import me.riley.HeroicEssentials;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WarpFile {

    private File file;
    private FileConfiguration config;
    public HeroicEssentials plugin;

    public WarpFile() {
        plugin = HeroicEssentials.getEssentials();
        file = new File(plugin.getDataFolder(), "warp.yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource("warp.yml", false);
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

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            config.load(file);
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

}
