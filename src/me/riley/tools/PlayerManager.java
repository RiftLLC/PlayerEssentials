package me.riley.tools;

import org.bukkit.Bukkit;

public class PlayerManager {

    public boolean playerExists(String name) {
        if (Bukkit.getServer().getPlayer(name) != null && Bukkit.getServer().getPlayer(name).getName().equalsIgnoreCase(name)) {
            return true;
        }

        return false;
    }

}
