package me.riley.commands.teleport.requests;

import me.riley.HeroicEssentials;
import me.riley.tools.PlayerManager;
import me.riley.tools.RequestManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPDenyCommand implements CommandExecutor {

    private RequestManager requestManager;

    public TPDenyCommand() {
        requestManager = HeroicEssentials.getEssentials().requestManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (requestManager.containsRequest(player)) {
                Player p2 = requestManager.getRequest(player);
                if (!(new PlayerManager().playerExists(args[0]))) {
                    player.sendMessage(cc("&cPlayer not found!"));
                    return true;
                }

                player.sendMessage(cc("&7You have denied &a" + p2.getName() + "&7's request."));
                p2.sendMessage(cc("&7Your teleportation request to &a" + player.getName() + " &7has been denied."));
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
