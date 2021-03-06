package dev.riftmc.essentials;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;

import javax.inject.Inject;
import java.util.logging.Logger;

@Plugin(id = "essentials", name = "essentials", version = "0.1.0", authors = "Quizaciously", description = "A Network's Needs.")
public class Essentials {

    private final ProxyServer server;
    private final Logger logger;

    @Inject
    public Essentials(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        int serverCount = server.getAllServers().size();
        logger.info("Found " + serverCount + " servers!");
    }

}
