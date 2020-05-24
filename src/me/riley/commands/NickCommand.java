package me.riley.commands;

import me.riley.tools.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommand implements CommandExecutor {

    private PlayerManager playerManager = new PlayerManager();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                if (playerManager.hasNickname(player)) {
                    String name = playerManager.getNickname(player);
                    player.sendMessage(cc("&7Your nickname is currently &a" + cc(name)));
                    return true;
                } else {
                    player.sendMessage(cc("&cYou don't have a nickname set"));
                }
            } else {
                String nick = args[0];
                if (nick.equalsIgnoreCase("none")) {
                    player.setDisplayName(player.getName());
                    playerManager.setNickname(null, player);
                    player.sendMessage(cc("&7Your nickname has been &creset"));
                } else {
                    player.setDisplayName(cc(nick));
                    playerManager.setNickname(nick, player);
                    player.sendMessage(cc("&7Your nickname is now &a" + nick));
                }
            }
        }

        return true;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
