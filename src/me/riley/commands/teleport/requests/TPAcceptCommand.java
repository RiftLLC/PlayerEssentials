package me.riley.commands.teleport.requests;

import me.riley.HeroicEssentials;
import me.riley.tools.PlayerManager;
import me.riley.tools.RequestManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPAcceptCommand implements CommandExecutor {

    private RequestManager requestManager;

    public TPAcceptCommand() {
        requestManager = HeroicEssentials.getEssentials().requestManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (requestManager.containsRequest(player)) {
                Player p2 = requestManager.getRequest(player);
                if (!(new PlayerManager().playerExists(p2.getName()))) {
                    player.sendMessage(cc("&cPlayer not found!"));
                    return true;
                }

                player.sendMessage(cc("&7Teleporting &a" + p2.getName() + " &7in &a5 &7seconds..."));
                p2.sendMessage(cc("&7Teleporting to " + player.getName() + "in &a5 &7seconds..."));

                Bukkit.getScheduler().runTaskLater(HeroicEssentials.getEssentials(), new Runnable() {

                    @Override
                    public void run() {
                        p2.teleport(player);

                        player.sendMessage(cc("&7Successfully teleported &a" + p2.getName()));
                        p2.sendMessage(cc("&7Successfully teleported to &a" + player.getName()));

                        requestManager.removeRequest(player);
                    }

                }, 20L * 5);
            } else {
                player.sendMessage(cc("&cNo requests could be found!"));
            }
        }

        return true;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
