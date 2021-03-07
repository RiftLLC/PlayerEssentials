package dev.riftmc.essentials;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;

import java.util.logging.Logger;

import static dev.riftmc.essentials.util.StringManager.cc;

public class Commands {

    private final Essentials essentials;
    private final ProxyServer server;
    private final Logger logger;
    private int commands = 0;

    public Commands(Essentials essentials, ProxyServer server, Logger logger) {
        this.essentials = essentials;
        this.server = server;
        this.logger = logger;
        register(getWebsiteCommand());
        register(getDiscordCommand());
    }

    public void register(BrigadierCommand command) {
        server.getCommandManager().register(command);
        commands++;
    }

    private BrigadierCommand getWebsiteCommand() {
        LiteralCommandNode<CommandSource> node = LiteralArgumentBuilder.<CommandSource>literal("website")
                .executes(context -> {
                    CommandSource source = context.getSource();
                    source.sendMessage(Identity.nil(), Component.text(""));
                    return 1;
                }).build();

        return new BrigadierCommand(node);
    }

    private BrigadierCommand getDiscordCommand() {
        LiteralCommandNode<CommandSource> node = LiteralArgumentBuilder
                .<CommandSource>literal("discord")
                .executes(context -> {
                    CommandSource source = context.getSource();
                    source.sendMessage(Identity.nil(), cc("&bDiscord: &7" + essentials.config.getConfig().getString("discord")));
                    return 1;
                }).build();

        return new BrigadierCommand(node);
    }

}
