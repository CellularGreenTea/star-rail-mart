package net.cgt.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConfig {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("src/main/java/net/cgt/service/db.properties"));
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }

    static String getUrl() {
        return properties.getProperty("db.url");
    }

    static String getUsername() {
        return properties.getProperty("db.username");
    }

    static String getPassword() {
        return properties.getProperty("db.password");
    }
}
