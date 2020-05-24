package me.riley.economy;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BankNote {

    public void giveBankNote(Player player, int amount) {
        ItemStack bankNote = createBankNote(amount);
        player.getInventory().addItem(bankNote);
    }

    private ItemStack createBankNote(int amount) {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(cc("&aBank Note"));
        List<String> lore = new ArrayList<String>();
        lore.add("" + amount);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
