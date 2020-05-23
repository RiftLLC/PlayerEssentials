package me.riley.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                if (!(player.getName().equalsIgnoreCase(player.getDisplayName()))) {
                    player.sendMessage(cc("&7Your nickname is currently &a" + player.getDisplayName()));
                    return true;
                } else {
                    player.sendMessage(cc("&cYou don't have a nickname set"));
                }
            } else {
                String nick = args[0];
                player.setDisplayName(cc(nick));
                player.sendMessage(cc("&7Your nickname is now &a" + nick));
            }
        }

        return true;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
