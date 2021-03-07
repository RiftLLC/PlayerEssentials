package dev.riftmc.essentials.util;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.List;

public class StringManager {

    public static TextComponent cc(String msg) {
        return LegacyComponentSerializer.legacy('&').deserialize(msg);
    }

    public static String combine(List<String> msg, String seperator) {
        StringBuilder builder = new StringBuilder();
        for (String s : msg) {
            builder.append(s).append(seperator);
        }

        return builder.toString();
    }

}