package me.riley.commands.gamemode;

import me.riley.tools.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(cc("&c/gamemode <mode> <player>"));
                return true;
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")) {
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(cc("&7Your gamemode has been set to &aCREATIVE"));
                } else if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s")) {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(cc("&7Your gamemode has been set to &aSURVIVAL"));
                } else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("sp")) {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(cc("&7Your gamemode has been set to &aSPECTATOR"));
                } else if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a")) {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(cc("&7Your gamemode has been set to &aADVENTURE"));
                }
            } else {
                if (!(new PlayerManager().playerExists(args[1]))) {
                    player.sendMessage(cc("&cThat player isn't online!"));
                    return true;
                }

                Player p2 = Bukkit.getPlayer(args[1]);
                if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")) {
                    p2.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(cc("&7You changed &a" + p2.getName() + "&7's gamemode to &aCREATIVE"));
                    p2.sendMessage(cc("&7Your gamemode has been changed to &aCREATIVE"));
                } else if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s")) {
                    p2.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(cc("&7You changed &a" + p2.getName() + "&7's gamemode to &aSURVIVAL"));
                    p2.sendMessage(cc("&7Your gamemode has been changed to &aSURVIVAL"));
                } else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("sp")) {
                    p2.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(cc("&7You changed &a" + p2.getName() + "&7's gamemode to &aSPECTATOR"));
                    p2.sendMessage(cc("&7Your gamemode has been changed to &aSPECTATOR"));
                } else if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a")) {
                    p2.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(cc("&7You changed &a" + p2.getName() + "&7's gamemode to &aADVENTURE"));
                    p2.sendMessage(cc("&7Your gamemode has been changed to &aADVENTURE"));
                }
            }
        }

        return true;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
