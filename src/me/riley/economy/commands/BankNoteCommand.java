package me.riley.economy.commands;

import me.riley.HeroicEssentials;
import me.riley.economy.BankNote;
import me.riley.economy.EconomyImplementer;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BankNoteCommand implements CommandExecutor {

    private EconomyImplementer economyImplementer = HeroicEssentials.getEssentials().economyImplementer;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                player.sendMessage(cc("&c/banknote <amount>"));
            } else {
                int amount = 0;
                try {
                    amount = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    player.sendMessage(cc("&cYou need to put a number for the amount"));
                }

                if(!economyImplementer.has(player, amount)) {
                    player.sendMessage(cc("&cYou do not have $" + amount));
                    return true;
                }

                economyImplementer.withdrawPlayer(player, amount);
                BankNote note = new BankNote();
                note.giveBankNote(player, amount);
                player.sendMessage(cc("&7You have been given a bank note worth $&a" + amount));
            }
        }

        return true;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
