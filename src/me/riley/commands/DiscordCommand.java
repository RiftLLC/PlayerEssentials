package me.riley.commands;

import me.riley.tools.Settings;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {

    private Settings settings = new Settings();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            String discordUri = settings.discord_uri;
            player.sendMessage(cc("&7Join our discord!"));
            player.sendMessage(cc("&a" + discordUri));
        }
        return true;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
