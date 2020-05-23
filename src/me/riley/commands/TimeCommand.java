package me.riley.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(cc("&7The current time in &a" + player.getWorld().getName() + " &7is &a" + getWorldTime(player)));
            } else {
                if (args.length == 1) {
                    String timeOfDay = args[0];
                    if (!setTime(player, timeOfDay)) {
                        player.sendMessage(cc("&c/time (day, noon, night, set) [time]"));
                    }
                } else {
                    if (args[0].equalsIgnoreCase("set")) {
                        if (isInt(args[1])) {
                            setTime(player, Integer.parseInt(args[1]));
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean setTime(Player player, String timeOfDay) {
        if (timeOfDay.equalsIgnoreCase("day")) {
            player.getWorld().setTime(3000);
            player.sendMessage(cc("&7Successfully set the time to &aday"));
        } else if (timeOfDay.equalsIgnoreCase("noon")) {
            player.getWorld().setTime(6000);
            player.sendMessage(cc("&7Successfully set the time to &anoon"));
        } else if (timeOfDay.equalsIgnoreCase("night")) {
            player.getWorld().setTime(13000);
            player.sendMessage(cc("&7Successfully set the time to &anight"));
        } else {
            return false;
        }

        return true;
    }

    private void setTime(Player player, int timeOfDay) {
        player.getWorld().setTime(timeOfDay);
        player.sendMessage(cc("&7Successfully set the time to &a" + timeOfDay));
    }

    private String getWorldTime(Player player) {
        long gameTime = player.getWorld().getTime(), hours = gameTime / 1000 + 6, minutes = (gameTime % 1000) * 60 / 1000;
        String ampm = "AM";
        if (hours >= 12) {
            hours -= 12;
            ampm = "PM";
        }
        if (hours >= 12) {
            hours -= 12;
            ampm = "AM";
        }
        if (hours == 0) hours = 12;
        String mm = "0" + minutes;
        mm = mm.substring(mm.length() - 2, mm.length());
        return hours + ":" + mm + " " + ampm;
    }

    private void setWorldTime(Player player, int time) {
        player.getWorld().setTime(time);
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
