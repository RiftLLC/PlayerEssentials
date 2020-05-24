package me.riley.commands.warps;

import me.riley.tools.WarpManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {

    WarpManager warpManager = new WarpManager();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("heroicessentails.warp.set")) {
                if (args.length == 0) {
                    player.sendMessage(cc("&c/setwarp <name>"));
                    return true;
                } else {
                    String name = args[0].toLowerCase();
                    if (!warpManager.exists(name)) {
                        warpManager.setWarp(name, player.getLocation());
                        player.sendMessage(cc("&7Successfully created warp &a" + name));
                    } else {
                        player.sendMessage(cc("&cThat warp already exists!"));
                    }
                }
            } else {
                player.sendMessage(cc("&cNo Permission!"));
            }
        }
        return true;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
