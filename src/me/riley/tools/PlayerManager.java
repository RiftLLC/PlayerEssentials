package me.riley.tools;

import me.riley.HeroicEssentials;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.net.InetAddress;

public class PlayerManager {

    private PlayerFile file = HeroicEssentials.getEssentials().playerFile;
    private HeroicEssentials plugin = HeroicEssentials.getEssentials();

    public boolean playerExists(String name) {
        if (Bukkit.getServer().getPlayer(name) != null && Bukkit.getServer().getPlayer(name).getName().equalsIgnoreCase(name)) {
            return true;
        }

        return false;
    }

    public void setNickname(String name, Player p) {
        file.getConfig().set("players." + p.getUniqueId() + ".nickname", name);
        file.saveConfig();
    }

    public String getNickname(Player p) {
        return file.getConfig().getString("players." + p.getUniqueId() + ".nickname");
    }

    public boolean hasNickname(Player p) {
        return file.getConfig().getString("players." + p.getUniqueId() + ".nickname") != null;
    }

    public boolean hasExistingBalance(Player p) {
        return file.getConfig().getInt("players." + p.getUniqueId() + ".balance") != 0;
    }

    public double getBalance(Player player) {
        return file.getConfig().getDouble("players." + player.getUniqueId() + ".balance");
    }

    public void setBalance(OfflinePlayer player, double amount) {
        file.getConfig().set("players." + player.getUniqueId() + ".balance", amount);
        file.saveConfig();
    }

    public void setIP(Player player, InetAddress address) {
        file.getConfig().set("players." + player.getUniqueId() + ".address", address.toString());
        file.saveConfig();
    }

    public String getIP(Player player) {
        return file.getConfig().getString("players." + player.getUniqueId() + ".address");
    }

    public void addPlayTime(Player player, int timePlayed) {
        int pastPlaytime = file.getConfig().getInt("players." + player.getUniqueId() + ".time");
        int playtime = pastPlaytime + timePlayed;
        file.getConfig().set("players." + player.getUniqueId() + ".time", playtime);
        file.saveConfig();
    }

    public int getTotalPlaytime(Player player) {
        int configPlaytime = file.getConfig().getInt("players." + player.getUniqueId() + ".time");
        int currentPlaytime = plugin.timePlayed.get(player);
        int playtime = configPlaytime + currentPlaytime;
        return playtime;
    }
}
