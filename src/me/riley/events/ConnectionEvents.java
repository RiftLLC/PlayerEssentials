package me.riley.events;

import me.riley.HeroicEssentials;
import me.riley.tools.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConnectionEvents implements Listener {

    private HeroicEssentials plugin = HeroicEssentials.getEssentials();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                for (String s : new Settings().join_message) {
                    String replaced = replace(s, player);
                    player.sendMessage(cc(replaced));
                }
            }
        }, 5L);
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
