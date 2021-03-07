package dev.riftmc.Pipeline.proxy;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;

import javax.inject.Inject;
import java.util.logging.Logger;

@Plugin(id = "pipeline", description = "A Pipleline Implementation", authors = "Quizaciously", version = "0.2-alpha.3")
public class PipelineVelocity {

    private final ProxyServer server;
    private final Logger logger;
    private int listeners;

    private MinecraftChannelIdentifier outgoing, incoming;

    @Inject // get the needed data from the server
    public PipelineVelocity(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
        logger.info("Pipeline is now starting!");
    }

    @Subscribe
    public void onInitialization(ProxyInitializeEvent event) {
        server.getChannelRegistrar().register(outgoing = MinecraftChannelIdentifier.create("pipeline", "incoming"));
        server.getChannelRegistrar().register(incoming = MinecraftChannelIdentifier.create("pipeline", "outgoing"));

        logger.info("Looking for listeners on " + server.getAllServers().size() + " servers!");
        // async task heree

        logger.info("Successfully found listeners on " + listeners + " servers!");

        logger.info("Pipeline has successfully initialized!");

    }

    @Subscribe
    public void onMessage(PluginMessageEvent event) {

    }


}
