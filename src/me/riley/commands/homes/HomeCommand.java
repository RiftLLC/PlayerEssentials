package me.riley.commands.homes;

import me.riley.HeroicEssentials;
import me.riley.tools.HomeManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class HomeCommand implements CommandExecutor {

    private HeroicEssentials plugin = HeroicEssentials.getEssentials();
    private HomeManager homeManager = new HomeManager();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                if (homeManager.exists(player, "home")) {
                    Location l = homeManager.getHome(player, "home");
                    player.teleport(l);
                    player.sendMessage(cc("&7You have been teleported to &ahome"));
                } else {
                    List<String> homes = homeManager.getAllHomes(player);
                    if (homes.isEmpty()) {
                        player.sendMessage(cc("&cYou have no homes!"));
                    } else {
                        StringBuilder sb = new StringBuilder("&7Available Homes: &a");
                        for (String home : homes) {
                            sb.append(home + "&7, &a");
                        }

                        player.sendMessage(cc(sb.toString()));
                    }
                }
            } else {
                String home = args[0];
                if (homeManager.exists(player, home)) {
                    Location l = homeManager.getHome(player, home);
                    player.teleport(l);
                    player.sendMessage(cc("&7You have been teleported to &a" + home));
                } else {
                    player.sendMessage(cc("&cThat home doesn't exist!"));
                }
            }
        }

        return true;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
