package dev.riftmc.PlayerEssentials.api;

import dev.riftmc.PlayerEssentials.PlayerEssentials;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.logging.Logger;

public class VaultHook {

    private final Logger logger = Logger.getLogger("Minecraft");
    private final PlayerEssentials plugin;

    public Economy economy;
    public Permission permissions;
    public Chat chat;

    public VaultHook(PlayerEssentials plugin) {
        this.plugin = plugin;

        if(hasVault()) {
            setupEconomy();
            setupPermissions();
            setupChat();
            logger.info("[VaultHook] Successfully hooked into Vault!");
        } else logger.info("[VaultHook] Couldn't find Vault! Please install it on your next restart!");
    }

    public boolean hasVault() {
        return plugin.getServer().getPluginManager().getPlugin("Vault") != null;
    }

    private boolean setupEconomy() {
        logger.info("[VaultHook] Economy has been registered!");
        RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        economy = rsp != null ? rsp.getProvider() : null;
        return true;
    }

    private boolean setupPermissions() {
        logger.info("[VaultHook] Permissions have been registered!");
        RegisteredServiceProvider<Permission> rsp = plugin.getServer().getServicesManager().getRegistration(Permission.class);
        permissions = rsp != null ? rsp.getProvider() : null;
        return permissions != null;
    }

    private boolean setupChat() {
        logger.info("[VaultHook] Chat has been registered!");
        RegisteredServiceProvider<Chat> rsp = plugin.getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp != null ? rsp.getProvider() : null;
        return chat != null;
    }

    public void remind() {
        if(!hasVault()) {
            logger.info("[VaultHook] Make sure to install Vault before your next startup!");
        }
    }
}
