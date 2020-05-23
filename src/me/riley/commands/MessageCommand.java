package me.riley.commands;

import me.riley.HeroicEssentials;
import me.riley.tools.PlayerManager;
import me.riley.tools.RequestManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

    private RequestManager requestManager = HeroicEssentials.getEssentials().requestManager;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0 || args.length == 1) {
                player.sendMessage(cc("&cUsage: /msg <player> <message>"));
                return true;
            } else {
                if (!(new PlayerManager().playerExists(args[0]))) {
                    player.sendMessage(cc("&cThat player doesn't exist!"));
                    return true;
                }

                Player p2 = Bukkit.getPlayer(args[0]);
                if (args.length > 2) {
                    StringBuilder message = new StringBuilder();
                    for (int x = 1; x < args.length; x++) {
                        message.append(args[x] + " ");
                    }

                    player.sendMessage(cc("&7[&aYou &7-> &a" + p2.getName() + "&7] &f" + message.toString()));
                    p2.sendMessage(cc("&7[&a" + player.getName() + " &7-> &aYou&7] &f" + message.toString()));
                    if (requestManager.messages.containsKey(player)) {
                        requestManager.messages.replace(player, p2);
                    } else {
                        requestManager.messages.put(player, p2);
                    }
                } else {
                    String message = args[1];
                    player.sendMessage(cc("&7[&aYou &7-> &a" + p2.getName() + "&7] &f" + message));
                    p2.sendMessage(cc("&7[&a" + player.getName() + " &7-> &aYou&7] &f" + message));
                    if (requestManager.messages.containsKey(player)) {
                        requestManager.messages.replace(player, p2);
                    } else {
                        requestManager.messages.put(player, p2);
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
