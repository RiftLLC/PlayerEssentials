package me.riley.economy.commands;

import me.riley.HeroicEssentials;
import me.riley.economy.BankNote;
import me.riley.economy.EconomyImplementer;
import me.riley.tools.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EconomyCommand implements CommandExecutor {

    private EconomyImplementer economyImplementer = HeroicEssentials.getEssentials().economyImplementer;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        // /eco give <player> <amount>
        // /eco take <player> <amount>
        // /eco givenote <player> <amount>
        // /eco reset <player>
        if (args.length <= 1) {
            sender.sendMessage(cc("&c/eco give <player> <amount>"));
            sender.sendMessage(cc("&c/eco take <player> <amount>"));
            sender.sendMessage(cc("&c/eco givenote <player> <amount>"));
            sender.sendMessage(cc("&c/eco reset <player>"));
            return true;
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("reset")) {
                if (new PlayerManager().playerExists(args[1])) {
                    Player player = Bukkit.getPlayer(args[1]);
                    economyImplementer.withdrawPlayer(player, economyImplementer.getBalance(player));
                    sender.sendMessage(cc("&7You successfully reset &a" + player.getName() + "&7's balance"));
                } else {
                    sender.sendMessage(cc("&cThat player doesn't exist!"));
                    return true;
                }
            } else {
                sender.sendMessage(cc("&c/eco give <player> <amount>"));
                sender.sendMessage(cc("&c/eco take <player> <amount>"));
                sender.sendMessage(cc("&c/eco givenote <player> <amount>"));
                sender.sendMessage(cc("&c/eco reset <player>"));
                return true;
            }
        } else {
            if (args[0].equalsIgnoreCase("give")) {
                if (new PlayerManager().playerExists(args[1])) {
                    int amount = 0;
                    try {
                        amount = Integer.parseInt(args[2]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage(cc("&cThe amount needs to be a number!"));
                    }
                    Player player = Bukkit.getPlayer(args[1]);
                    economyImplementer.depositPlayer(player, amount);
                    sender.sendMessage(cc("&7You've successfully added $&a" + amount + " &7to &a" + player.getName() + "&7's balance"));
                } else {
                    sender.sendMessage(cc("&cThat player doesn't exist!"));
                    return true;
                }
            } else if (args[0].equalsIgnoreCase("take")) {
                if (new PlayerManager().playerExists(args[1])) {
                    int amount = 0;
                    try {
                        amount = Integer.parseInt(args[2]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage(cc("&cThe amount needs to be a number!"));
                    }
                    Player player = Bukkit.getPlayer(args[1]);
                    economyImplementer.withdrawPlayer(player, amount);
                    sender.sendMessage(cc("&7You've successfully taken $&a" + amount + " &7from &a" + player.getName() + "&7's balance"));
                } else {
                    sender.sendMessage(cc("&cThat player doesn't exist!"));
                    return true;
                }
            } else if (args[0].equalsIgnoreCase("givenote")) {
                if (new PlayerManager().playerExists(args[1])) {
                    int amount = 0;
                    try {
                        amount = Integer.parseInt(args[2]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage(cc("&cThe amount needs to be a number!"));
                    }

                    Player player = Bukkit.getPlayer(args[1]);
                    new BankNote().giveBankNote(player, amount);
                    sender.sendMessage(cc("&7You've successfully given a bank note worth $&a" + amount + " &7to &a" + player.getName()));
                } else {
                    sender.sendMessage(cc("&cThat player doesn't exist!"));
                    return true;
                }
            } else {
                sender.sendMessage(cc("&c/eco give <player> <amount>"));
                sender.sendMessage(cc("&c/eco take <player> <amount>"));
                sender.sendMessage(cc("&c/eco givenote <player> <amount>"));
                sender.sendMessage(cc("&c/eco reset <player>"));
                return true;
            }
        }

        return true;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
