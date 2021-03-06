package dev.riftmc.PlayerEssentials.tools;

import dev.riftmc.PlayerEssentials.Essentials;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Logger;

public class VersionChecker {

    public static boolean upToDate = false;
    public static String latest = "", current = "";
    private final Logger logger = Logger.getLogger("Minecraft");

    public VersionChecker(Essentials plugin) {
        current = plugin.getDescription().getVersion();
        check();
    }

    void check() {
        logger.info("[Update Checker] Checking to see if you are on the latest version...");
        InputStream in = null;

        try {
            in = new URL("https://raw.githubusercontent.com/RiftLLC/PlayerEssentials/master/version.txt").openStream();
            latest = IOUtils.readLines(in).get(0);
        } catch (IOException e) {
            logger.info("[Update Checker] Unable to determine version!");
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }

        logger.info("[Update Checker] Latest version is " + latest + "!");
        upToDate = current.equals(latest);

        if (upToDate)
            logger.info("[Update Checker] You are currently running the latest version!");
        else
            logger.info("[Update Checker] You are running an outdated version, please upgrade!");
    }

    public void remind() {
        if (upToDate)
            logger.info("[Update Checker] You are currently running the latest version!");
        else
            logger.info("[Update Checker] You are running an outdated version, please upgrade!");
    }

}
