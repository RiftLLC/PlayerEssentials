package me.riley.economy.commands;

import me.riley.HeroicEssentials;
import me.riley.economy.EconomyImplementer;
import me.riley.tools.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand implements CommandExecutor {

    private EconomyImplementer economyImplementer = HeroicEssentials.getEssentials().economyImplementer;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 2) {
                player.sendMessage(cc("&c/pay <player> <amount>"));
            } else {
                double amount = 0;
                try {
                    amount = Double.parseDouble(args[1]);
                } catch (NumberFormatException e) {
                    player.sendMessage(cc("&cYou need to put a number for the amount"));
                }

                if (new PlayerManager().playerExists(args[0])) {
                    Player p2 = Bukkit.getPlayer(args[0]);
                    economyImplementer.withdrawPlayer(player, amount);
                    economyImplementer.depositPlayer(p2, amount);

                    player.sendMessage(cc("&7You payed &a" + p2.getName() + " &7$&a" + amount));
                    p2.sendMessage(cc("&a" + player.getDisplayName() + " &7payed you $&a" + amount));
                } else {
                    player.sendMessage(cc("&cThat player isn't online!"));
                }
            }
        }

        return true;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
