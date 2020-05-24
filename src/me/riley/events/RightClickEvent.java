package me.riley.events;

import me.riley.HeroicEssentials;
import me.riley.economy.EconomyImplementer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class RightClickEvent implements Listener {

    private EconomyImplementer economyImplementer = HeroicEssentials.getEssentials().economyImplementer;

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
<<<<<<< Updated upstream
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(cc("&aBank Note"))) {
                String amountString = ChatColor.RESET + item.getItemMeta().getLore().get(0).replace("$", "");
=======
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            Player player = event.getPlayer();
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(cc("&aBank Note"))) {
                String amountString = ChatColor.stripColor(item.getItemMeta().getLore().get(0));
>>>>>>> Stashed changes
                int amountPre = 0;
                try {
                    amountPre = Integer.parseInt(amountString);
                } catch (NumberFormatException e) {
                    player.sendMessage(cc("&cCouldn't cash the bank note..."));
                    return;
                }

                int itemAmount = item.getAmount();
                int amount = amountPre * itemAmount;
<<<<<<< Updated upstream
                player.getInventory().remove(item);
=======
                event.getItem().setAmount(0);
>>>>>>> Stashed changes
                economyImplementer.depositPlayer(player, amount);
                player.sendMessage(cc("&7Successfully cashed your bank notes for $&a" + amount));
            }
        }
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
