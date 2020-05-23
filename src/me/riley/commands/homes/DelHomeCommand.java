package me.riley.commands.homes;

import me.riley.tools.HomeManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelHomeCommand implements CommandExecutor {

    private HomeManager homeManager = new HomeManager();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                homeManager.deleteHome(player, "home");
                player.sendMessage(cc("&ahome &7has been deleted"));
            } else {
                String home = args[0].toLowerCase();
                homeManager.deleteHome(player, home);
                player.sendMessage(cc("&a" + home + " &7has been deleted"));
            }
        }

        return true;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
