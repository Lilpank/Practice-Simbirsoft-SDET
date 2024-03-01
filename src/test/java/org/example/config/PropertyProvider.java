package org.example.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyProvider {
    private static final Properties envProperties = new Properties();
    private final static String resourcePath = "src/test/resources/";

    static {
        try {
            envProperties.load(new FileInputStream(resourcePath + "env.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * A method to provide data for API URL.
     *
     * @param key key from property file, example - api.url
     * @return url for API
     */
    public static String getProperty(String key) {
        return envProperties.getProperty(key);
    }
}
