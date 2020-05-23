package me.riley.commands.homes;

import me.riley.tools.HomeManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class HomesCommand implements CommandExecutor {

    private HomeManager homeManager = new HomeManager();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            List<String> homes = homeManager.getAllHomes(player);
            if (homes.isEmpty()) {
                player.sendMessage(cc("&cYou have no homes"));
            } else {
                StringBuilder sb = new StringBuilder("&7Available Homes: &a");
                for (String home : homes) {
                    sb.append(home + "&7, &a");
                }

                player.sendMessage(cc(sb.toString()));
            }
        }

        return true;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
