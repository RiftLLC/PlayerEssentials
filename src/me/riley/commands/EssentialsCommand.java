package me.riley.commands;

import me.riley.HeroicEssentials;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EssentialsCommand implements CommandExecutor {

    private HeroicEssentials plugin;
    public EssentialsCommand() {
        plugin = HeroicEssentials.getEssentials();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("heroicessentials.heroicessentials")) {
                if(args.length == 0) {
                    player.sendMessage(cc("&7Heroic Essentials &av" + plugin.getDescription().getVersion() + " &7by &aRiley Calhoun"));
                } else {
                    if(args[0].equalsIgnoreCase("reload")) {
                        plugin.reloadAllConfigs();
                        player.sendMessage(cc("&7All configs have been reloaded!"));
                    }
                }
            } else {
                player.sendMessage(cc("&cNo Permission."));
            }
        }

        return true;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
