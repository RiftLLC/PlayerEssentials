package dev.riftmc.PlayerEssentials.tools;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class ColoredMessage {

    public static TextComponent cc(String msg) {
        return LegacyComponentSerializer.legacy('&').deserialize(msg);
    }

}
