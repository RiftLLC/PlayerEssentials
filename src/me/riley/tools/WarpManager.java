package me.riley.tools;

import me.riley.HeroicEssentials;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class WarpManager {

    private HeroicEssentials plugin = HeroicEssentials.getEssentials();

    public String setWarp(String name, Location l) {
        int x = l.getBlockX();
        int y = l.getBlockY();
        int z = l.getBlockZ();
        float yaw = l.getYaw();
        float pitch = l.getPitch();
        String world = l.getWorld().getName();
        plugin.warpFile.getConfig().set("warps." + name + ".x", x);
        plugin.warpFile.getConfig().set("warps." + name + ".y", y);
        plugin.warpFile.getConfig().set("warps." + name + ".z", z);
        plugin.warpFile.getConfig().set("warps." + name + ".yaw", yaw);
        plugin.warpFile.getConfig().set("warps." + name + ".pitch", pitch);
        plugin.warpFile.getConfig().set("warps." + name + ".world", world);
        plugin.warpFile.save();
        return "&7Warp &a" + name + " &7has been created successfully.";
    }

    public String deleteWarp(String name) {
        plugin.warpFile.getConfig().set("warps." + name + ".x", null);
        plugin.warpFile.getConfig().set("warps." + name + ".y", null);
        plugin.warpFile.getConfig().set("warps." + name + ".z", null);
        plugin.warpFile.getConfig().set("warps." + name + ".yaw", null);
        plugin.warpFile.getConfig().set("warps." + name + ".pitch", null);
        plugin.warpFile.getConfig().set("warps." + name + ".world", null);
        plugin.warpFile.save();
        return "&7Warp &a" + name + " &7has been deleted successfully.";
    }

    public Location getWarp(String name) {
        int x = plugin.warpFile.getConfig().getInt("warps." + name + ".x");
        int y = plugin.warpFile.getConfig().getInt("warps." + name + ".y");
        int z = plugin.warpFile.getConfig().getInt("warps." + name + ".z");
        float yaw = (float) plugin.warpFile.getConfig().getDouble("warps." + name + ".yaw");
        float pitch = (float) plugin.warpFile.getConfig().getDouble("warps." + name + ".pitch");
        String world = plugin.warpFile.getConfig().getString("warps." + name + ".world");
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }


    public List<String> getAllWarps() {
        List<String> warps = new ArrayList<>();
        if (plugin.warpFile.getConfig().getConfigurationSection("warps") != null) {
            for (String warp : plugin.warpFile.getConfig().getConfigurationSection("warps").getKeys(false)) {
                warps.add(warp);
            }
        }

        return warps;
    }

    public boolean exists(String name) {
        return (plugin.warpFile.getConfig().getConfigurationSection("warps." + name) != null);
    }

}
