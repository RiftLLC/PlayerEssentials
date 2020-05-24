package me.riley.commands.warps;

import me.riley.HeroicEssentials;
import me.riley.tools.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {

    private HeroicEssentials plugin = HeroicEssentials.getEssentials();
    private WarpManager warpManager = new WarpManager();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("heroicessentials.warp")) {
                if (args.length == 0) {
                    // List Warps
                    StringBuilder sb = new StringBuilder("&7Available Warps: ");
                    for (String warp : warpManager.getAllWarps()) {
                        sb.append("&a" + warp + "&7, ");
                    }

                    player.sendMessage(cc(sb.toString()));
                } else {
                    String warp = args[0];
                    if (warpManager.exists(warp)) {
                        Location warpLoc = warpManager.getWarp(warp);
                        player.sendMessage(cc("&7Teleporting in &a5 &7seconds..."));
                        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                            @Override
                            public void run() {
                                player.sendMessage(cc("&7Teleporting..."));
                                player.teleport(warpLoc);
                            }
                        }, 20L * 5);
                    } else {
                        player.sendMessage(cc("&cThat warp doesn't exist!"));
                    }
                }
            }
        }

        return true;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
