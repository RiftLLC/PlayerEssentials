package me.riley.tools;

import me.riley.HeroicEssentials;

import java.util.List;

public class Settings {

    private HeroicEssentials plugin = HeroicEssentials.getEssentials();

    public String discord_uri = plugin.getConfig().getString("discord-uri");
    public String store_uri = plugin.getConfig().getString("store-uri");
    public String terms_uri = plugin.getConfig().getString("terms-uri");

    public String[] MOTD = {plugin.getConfig().getString("motd.line-1"), plugin.getConfig().getString("motd.line-2")};
    public List<String> join_message = plugin.getConfig().getStringList("join-message");

}
