package dev.riftmc.essentials.util;

import com.moandjiezana.toml.Toml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigFile {

    private final Toml config;

    public ConfigFile(final Path folder, String fileName) {
        config = loadConfig(folder, fileName);
    }

    public Toml loadConfig(Path path, String fileName) {
        File folder = path.toFile();
        File file = new File(folder, fileName + ".toml");
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        if (!file.exists()) {
            try (InputStream input = getClass().getResourceAsStream("/" + file.getName())) {
                if (input != null) {
                    Files.copy(input, file.toPath());
                } else {
                    file.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        return new Toml().read(file);
    }

    public Toml getConfig() {
        return config;
    }

}
