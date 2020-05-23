package me.riley.commands;

import me.riley.HeroicEssentials;
import me.riley.tools.RequestManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCommand implements CommandExecutor {

    private HeroicEssentials plugin = HeroicEssentials.getEssentials();
    private RequestManager requestManager = plugin.requestManager;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (requestManager.messages.containsKey(player) || requestManager.messages.containsValue(player)) {
                Player p2;
                if (requestManager.messages.containsValue(player)) p2 = getRequestBack(player);
                else p2 = requestManager.messages.get(player);

                if (args.length == 0) {
                    player.sendMessage(cc("&cYou need to input a message"));
                    return true;
                } else if (args.length == 1) {
                    String message = args[0];
                    player.sendMessage(cc("&7[&aYou &7-> &a" + p2.getName() + "&7] &f" + message));
                    p2.sendMessage(cc("&7[&a" + player.getName() + " &7-> &aYou&7] &f" + message));
                } else {
                    StringBuilder message = new StringBuilder();
                    for (int x = 0; x < args.length; x++) {
                        message.append(args[x] + " ");
                    }

                    player.sendMessage(cc("&7[&aYou &7-> &a" + p2.getName() + "&7] &f" + message.toString()));
                    p2.sendMessage(cc("&7[&a" + player.getName() + " &7-> &aYou&7] &f" + message.toString()));
                }
            } else {
                player.sendMessage(cc("&cYou have no one to reply to."));
            }
        }

        return true;
    }

    public Player getRequestBack(Player player) {
        for (Player p : requestManager.messages.keySet()) {
            if (requestManager.messages.get(p) == player) {
                return p;
            }
        }

        return null;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
