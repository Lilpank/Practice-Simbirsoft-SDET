package org.example.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyProvider {
    private static final Properties envProperties = new Properties();
    private final static String rootPath = "src/test/resources/";

    static {
        try {
            envProperties.load(new FileInputStream(rootPath + "env.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPropertyWebUrl() {
        return envProperties.getProperty("web.url");
    }
}
