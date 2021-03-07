package dev.riftmc.Pipeline.server;

import dev.riftmc.PlayerEssentials.Essentials;
import dev.riftmc.PlayerEssentials.api.EssentialsHook;
import org.bukkit.plugin.java.JavaPlugin;

public class PipelineBukkit extends JavaPlugin {

    public Essentials essentials;

    @Override
    public void onEnable() {
        essentials = new EssentialsHook().getEssentials();

    }

}
