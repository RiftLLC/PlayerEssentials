package me.riley.commands.homes;

import me.riley.HeroicEssentials;
import me.riley.tools.HomeManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {

    private HeroicEssentials plugin = HeroicEssentials.getEssentials();
    private HomeManager homeManager = new HomeManager();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                homeManager.setHome(player, player.getLocation(), "home");
                player.sendMessage(cc("&7Home &ahome &7has been set"));
            } else {
                String home = args[0].toLowerCase();
                if (player.hasPermission("heroicessentials.homes.multiple")) {
                    homeManager.setHome(player, player.getLocation(), home);
                    player.sendMessage(cc("&7Home &a" + home + " &7has been set"));
                } else {
                    if (home.equals("home")) {
                        homeManager.setHome(player, player.getLocation(), "home");
                        player.sendMessage(cc("&7Home &ahome &7has been set"));
                        plugin.playerFile.saveConfig();
                    } else {
                        player.sendMessage(cc("&cYou don't have permission for multiple homes"));
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
