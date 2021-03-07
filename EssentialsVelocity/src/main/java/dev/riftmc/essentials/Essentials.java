package dev.riftmc.essentials;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.riftmc.essentials.util.ConfigFile;

import javax.inject.Inject;
import java.nio.file.Path;
import java.util.logging.Logger;

@Plugin(id = "essentials", name = "essentials", version = "0.1.0", authors = "Quizaciously", description = "A Network's Needs.")
public class Essentials {

    private final ProxyServer server;
    private final Logger logger;
    public ConfigFile config;
    public Commands commands;

    @Inject
    public Essentials(ProxyServer server, Logger logger, @DataDirectory final Path folder) {
        this.server = server;
        this.logger = logger;
        this.config = new ConfigFile(folder, "config");
        commands = new Commands(this, server, logger);
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        int serverCount = server.getAllServers().size();

        logger.info("Found " + serverCount + " servers!");
    }

}
