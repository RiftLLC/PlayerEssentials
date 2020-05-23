package me.riley.commands.gamemode;

import me.riley.tools.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SurvivalCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("heroicessentials.gamemode")) {
                if(args.length == 0) {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(cc("&7Your gamemode has been set to &aSURVIVAL"));
                } else {
                    if(!(new PlayerManager().playerExists(args[0]))) {
                        player.sendMessage(cc("&cPlayer not found!"));
                        return true;
                    }

                    Player p2 = Bukkit.getPlayer(args[0]);
                    p2.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(cc("&7You changed &a" + p2.getName() + "&7's gamemode to &aSURVIVAL"));
                    p2.sendMessage(cc("&7Your gamemode has been changed to &aSURVIVAL"));
                }
            } else {
                player.sendMessage(cc("&cNo Permission."));
            }
        }
        return true;
    }

    public String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
