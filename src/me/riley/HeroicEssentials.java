package me.riley;

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
import me.riley.events.ConnectionEvents;
import me.riley.events.ServerListEvent;
import me.riley.tools.GithubLoading;
import me.riley.tools.PlayerFile;
import me.riley.tools.RequestManager;
import org.bukkit.plugin.java.JavaPlugin;

public class HeroicEssentials extends JavaPlugin {

    public RequestManager requestManager;
    public PlayerFile playerFile;
    public WarpFile warpFile;

    @Override
    public void onEnable() {
        String IP = getServer().getIp() + ":" + getServer().getPort();
        if (GithubLoading.readIPS().contains(IP)) {
            essentials = this;
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

            getCommand("nick").setExecutor(new NickCommand());

            getServer().getPluginManager().registerEvents(new ServerListEvent(), this);
            getServer().getPluginManager().registerEvents(new ConnectionEvents(), this);
        } else {
            getServer().getLogger().info("Shutting down, not on our list!");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    private static HeroicEssentials essentials;

    public static HeroicEssentials getEssentials() {
        return essentials;
    }

    public void reloadAllConfigs() {
        reloadConfig();
        playerFile.reloadConfig();
    }
}
