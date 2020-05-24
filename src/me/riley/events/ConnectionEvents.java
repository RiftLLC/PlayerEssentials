package me.riley.events;

import com.sun.xml.internal.ws.resources.UtilMessages;
import me.riley.HeroicEssentials;
import me.riley.tools.PlayerManager;
import me.riley.tools.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class ConnectionEvents implements Listener {

    private HeroicEssentials plugin = HeroicEssentials.getEssentials();
    private PlayerManager playerManager = new PlayerManager();
    private HashMap<Player, Integer> id = new HashMap<>();
    private BukkitTask t;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.playerBank.put(player.getUniqueId(), 0D);
        if(playerManager.hasExistingBalance(player)) {
            double balance = playerManager.getBalance(player);
            plugin.economyImplementer.depositPlayer(player, balance);
        } else {
            plugin.economyImplementer.depositPlayer(player, 5000);
        }

        if (playerManager.hasNickname(player)) {
            player.setDisplayName(playerManager.getNickname(player));
        }

        playerManager.setIP(player, player.getAddress().getAddress());

        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                for (String s : new Settings().join_message) {
                    String replaced = replace(s, player);
                    player.sendMessage(cc(replaced));
                }
            }
        }, 5L);

        plugin.timePlayed.put(player, 0);

        t = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                int seconds = plugin.timePlayed.get(player) + 1;
                plugin.timePlayed.replace(player, seconds);
                id.put(player, t.getTaskId());
            }
        }, 20L, 0L);
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        plugin.playerBank.remove(player.getUniqueId());
        BukkitTask task = (BukkitTask) Bukkit.getScheduler().getActiveWorkers().get(id.get(player));
        task.cancel();

        int timePlayed = plugin.timePlayed.get(player);
        new PlayerManager().addPlayTime(player, timePlayed);
    }

    private String replace(String m, Player player) {
        m = m.replace("%player%", player.getDisplayName());
        m = m.replace("%online%", Integer.toString(player.getServer().getOnlinePlayers().size()));
        return m;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
