package me.riley.economy.commands;

import me.riley.HeroicEssentials;
import me.riley.economy.EconomyImplementer;
import me.riley.tools.PlayerManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {

    private HeroicEssentials plugin = HeroicEssentials.getEssentials();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                // own balance
                double balance = plugin.economyImplementer.getBalance(player);
                player.sendMessage(cc("&7Your Balance: &a" + balance));
            } else {
                if (new PlayerManager().playerExists(args[0])) {
                    Player p2 = Bukkit.getPlayer(args[0]);
                    double balance = plugin.economyImplementer.getBalance(p2);
                    player.sendMessage(cc("&a" + p2.getDisplayName() + "&7's Balance: &a" + balance));
                } else {
                    player.sendMessage(cc("&cThat player doesn't exist!"));
                    return true;
                }
            }
        }

        return true;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
