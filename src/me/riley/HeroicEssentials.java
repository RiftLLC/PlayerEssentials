package me.riley;

import com.sun.deploy.cache.BaseLocalApplicationProperties;
import me.riley.commands.*;
import me.riley.commands.gamemode.CreativeCommand;
import me.riley.commands.gamemode.GamemodeCommand;
import me.riley.commands.gamemode.SurvivalCommand;
import me.riley.commands.homes.DelHomeCommand;
import me.riley.commands.homes.HomeCommand;
import me.riley.commands.homes.HomesCommand;
import me.riley.commands.homes.SetHomeCommand;
import me.riley.commands.teleport.TPCommand;
import me.riley.commands.teleport.TPHereCommand;
import me.riley.commands.teleport.requests.TPACommand;
import me.riley.commands.teleport.requests.TPAcceptCommand;
import me.riley.commands.teleport.requests.TPDenyCommand;
import me.riley.commands.warps.DelWarpCommand;
import me.riley.commands.warps.SetWarpCommand;
import me.riley.commands.warps.WarpCommand;
import me.riley.economy.EconomyImplementer;
import me.riley.economy.VaultHook;
import me.riley.economy.commands.BalanceCommand;
import me.riley.economy.commands.BankNoteCommand;
import me.riley.economy.commands.PayCommand;
import me.riley.events.ConnectionEvents;
import me.riley.events.RightClickEvent;
import me.riley.events.ServerListEvent;
import me.riley.tools.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class HeroicEssentials extends JavaPlugin {

    public RequestManager requestManager;
    public PlayerFile playerFile;
    public WarpFile warpFile;
    public final HashMap<UUID, Double> playerBank = new HashMap<>();
    public EconomyImplementer economyImplementer;
    public HashMap<Player, Integer> timePlayed = new HashMap<>();
    private VaultHook vaultHook;

    @Override
    public void onEnable() {
        String IP = getServer().getIp() + ":" + getServer().getPort();
//        if (GithubLoading.readIPS().contains(IP)) {
            essentials = this;
            economyImplementer = new EconomyImplementer();
            vaultHook = new VaultHook();
            vaultHook.hook();
            requestManager = new RequestManager();
            playerFile = new PlayerFile();
            warpFile = new WarpFile();

            getConfig().options().copyDefaults(true);
            saveConfig();

            getCommand("heroicessentials").setExecutor(new EssentialsCommand());
            getCommand("time").setExecutor(new TimeCommand());
            getCommand("discord").setExecutor(new DiscordCommand());
            getCommand("store").setExecutor(new StoreCommand());

            getCommand("tpa").setExecutor(new TPACommand());
            getCommand("tpaccept").setExecutor(new TPAcceptCommand());
            getCommand("tpdeny").setExecutor(new TPDenyCommand());
            getCommand("teleport").setExecutor(new TPCommand());
            getCommand("tphere").setExecutor(new TPHereCommand());

            getCommand("gmc").setExecutor(new CreativeCommand());
            getCommand("gms").setExecutor(new SurvivalCommand());
            getCommand("gamemode").setExecutor(new GamemodeCommand());

            getCommand("tell").setExecutor(new MessageCommand());
            getCommand("reply").setExecutor(new ReplyCommand());

            getCommand("home").setExecutor(new HomeCommand());
            getCommand("sethome").setExecutor(new SetHomeCommand());
            getCommand("delhome").setExecutor(new DelHomeCommand());
            getCommand("homes").setExecutor(new HomesCommand());

            getCommand("warp").setExecutor(new WarpCommand());
            getCommand("setwarp").setExecutor(new SetWarpCommand());
            getCommand("delwarp").setExecutor(new DelWarpCommand());

            getCommand("nick").setExecutor(new NickCommand());
            getCommand("pay").setExecutor(new PayCommand());
            getCommand("banknotes").setExecutor(new BankNoteCommand());
            getCommand("balance").setExecutor(new BalanceCommand());

            getServer().getPluginManager().registerEvents(new ServerListEvent(), this);
            getServer().getPluginManager().registerEvents(new ConnectionEvents(), this);
            getServer().getPluginManager().registerEvents(new RightClickEvent(), this);

            if (!Bukkit.getOnlinePlayers().isEmpty()) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    playerBank.put(player.getUniqueId(), 0D);
                    if(new PlayerManager().hasExistingBalance(player)) {
                        double balance = new PlayerManager().getBalance(player);
                        economyImplementer.depositPlayer(player, balance);
                    } else {
                        economyImplementer.depositPlayer(player, 5000);
                    }
                }
            }
<<<<<<< Updated upstream
        } else {
            getServer().getLogger().info("Shutting down, not on our list!");
            getServer().getPluginManager().disablePlugin(this);
        }
=======
//        } else {
//            getServer().getLogger().info("Shutting down, not on our list!");
//            getServer().getPluginManager().disablePlugin(this);
//        }
    }

    public void onDisable() {
        vaultHook.unhook();
>>>>>>> Stashed changes
    }

    public void onDisable() {
        vaultHook.unhook();
    }

    private static HeroicEssentials essentials;

    public static HeroicEssentials getEssentials() {
        return essentials;
    }

    public void reloadAllConfigs() {
        reloadConfig();
        playerFile.reloadConfig();
        warpFile.reload();
    }
}
