package com.cybersergi.cryptoanalyzer.config;

import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Configuration
public class ConfigProperties {

    private static final Properties properties = new Properties();
    private static final Path envFile = Paths.get("CryptoAnalyzer/.env");

    public ConfigProperties() {
        loadEnv();
    }

    private void loadEnv() {
        if (!Files.exists(envFile)) {
            System.err.println(".env file not found: " + envFile.toAbsolutePath());
            return;
        }

        try {
            Files.lines(envFile)
                    .filter(line -> !line.trim().isEmpty())
                    .filter(line -> !line.trim().startsWith("#"))
                    .forEach(line -> {
                        int idx = line.indexOf('=');
                        if (idx > 0) {
                            String key = line.substring(0, idx).trim();
                            String value = line.substring(idx + 1).trim();

                            properties.setProperty(key, value);
                            System.setProperty(key, value);

                            System.out.println("Loaded env: " + key);
                        }
                    });
        } catch (IOException exception) {
            System.err.println("Unable to load .env file: " + exception.getMessage());
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
