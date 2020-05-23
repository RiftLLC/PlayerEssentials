package me.riley.commands.teleport.requests;

import me.riley.HeroicEssentials;
import me.riley.tools.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPACommand implements CommandExecutor {

    private HeroicEssentials plugin;

    public TPACommand() {
        plugin = HeroicEssentials.getEssentials();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(cc("&cUsage: &7/tpa <player>"));
                return true;
            } else {
                if (!(new PlayerManager().playerExists(args[0]))) {
                    player.sendMessage(cc("&cPlayer not found!"));
                    return true;
                }

                Player p2 = Bukkit.getPlayer(args[0]);

                plugin.requestManager.addRequest(p2, player);
                player.sendMessage(cc("&7You have requested to teleport to &a" + p2.getName()));
                p2.sendMessage(cc("&a" + player.getName() + " &7has requested to teleport to you."));
                p2.sendMessage(cc("&a/tpaccept &7to accept, &a/tpdeny &7to deny"));
                return true;
            }
        }

        return true;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
