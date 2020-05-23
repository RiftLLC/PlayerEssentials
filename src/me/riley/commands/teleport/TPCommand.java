package me.riley.commands.teleport;

import me.riley.tools.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("heroicessentials.teleport")) {
                if (args.length == 0) {
                    player.sendMessage(cc("&c/tp <player>"));
                    return true;
                } else {
                    if (!(new PlayerManager().playerExists(args[0]))) {
                        player.sendMessage(cc("&cPlayer not found!"));
                        return true;
                    }

                    Player p2 = Bukkit.getPlayer(args[0]);

                    player.teleport(p2);
                    player.sendMessage(cc("&7You teleported to &a" + p2.getName()));
                }
            } else {
                player.sendMessage(cc("&cNo Permission."));
                return true;
            }
        }

        return true;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
