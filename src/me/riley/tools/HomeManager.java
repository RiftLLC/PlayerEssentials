package me.riley.tools;

import me.riley.HeroicEssentials;
import net.minecraft.server.v1_15_R1.IPlayerFileData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeManager {

    public HeroicEssentials plugin = HeroicEssentials.getEssentials();

    public void setHome(Player p, Location location, String home) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        float yaw = location.getYaw();
        float pitch = location.getPitch();
        World world = location.getWorld();
        plugin.playerFile.getConfig().set("players." + p.getUniqueId() + ".homes." + home + ".x", x);
        plugin.playerFile.getConfig().set("players." + p.getUniqueId() + ".homes." + home + ".y", y);
        plugin.playerFile.getConfig().set("players." + p.getUniqueId() + ".homes." + home + ".z", z);
        plugin.playerFile.getConfig().set("players." + p.getUniqueId() + ".homes." + home + ".yaw", yaw);
        plugin.playerFile.getConfig().set("players." + p.getUniqueId() + ".homes." + home + ".pitch", pitch);
        plugin.playerFile.getConfig().set("players." + p.getUniqueId() + ".homes." + home + ".world", world.getName());
        plugin.playerFile.saveConfig();
    }

    public void deleteHome(Player p, String home) {
        plugin.playerFile.getConfig().set("players." + p.getUniqueId() + ".homes." + home, null);
        plugin.playerFile.getConfig().set("players." + p.getUniqueId() + ".homes." + home + ".x", null);
        plugin.playerFile.getConfig().set("players." + p.getUniqueId() + ".homes." + home + ".y", null);
        plugin.playerFile.getConfig().set("players." + p.getUniqueId() + ".homes." + home + ".z", null);
        plugin.playerFile.getConfig().set("players." + p.getUniqueId() + ".homes." + home + ".yaw", null);
        plugin.playerFile.getConfig().set("players." + p.getUniqueId() + ".homes." + home + ".pitch", null);
        plugin.playerFile.getConfig().set("players." + p.getUniqueId() + ".homes." + home + ".world", null);
        plugin.playerFile.saveConfig();
    }

    public Location getHome(Player p, String home) {
        int x = plugin.playerFile.getConfig().getInt("players." + p.getUniqueId() + ".homes." + home + ".x");
        int y = plugin.playerFile.getConfig().getInt("players." + p.getUniqueId() + ".homes." + home + ".y");
        int z = plugin.playerFile.getConfig().getInt("players." + p.getUniqueId() + ".homes." + home + ".z");
        float yaw = (float) plugin.playerFile.getConfig().getDouble("players." + p.getUniqueId() + ".homes." + home + ".yaw");
        float pitch = (float) plugin.playerFile.getConfig().getDouble("players." + p.getUniqueId() + ".homes." + home + ".pitch");
        World world = Bukkit.getWorld(Objects.requireNonNull(plugin.playerFile.getConfig().getString("players." + p.getUniqueId() + ".homes." + home + ".world")));
        Location l = new Location(world, x, y, z, yaw, pitch);
        return l;
    }

    public List<String> getAllHomes(Player p) {
        List<String> homes = new ArrayList<String>();
        if (plugin.playerFile.getConfig().getConfigurationSection("players." + p.getUniqueId() + ".homes") != null) {
            for (String s : Objects.requireNonNull(plugin.playerFile.getConfig().getConfigurationSection("players." + p.getUniqueId() + ".homes")).getKeys(false)) {
                homes.add(s);
            }
        }
        return homes;
    }

    public boolean exists(Player p, String home) {
        return plugin.playerFile.getConfig().getConfigurationSection("players." + p.getUniqueId() + ".homes." + home) != null;
    }

}
