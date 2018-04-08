package com.gmail.buckartz.coffee_shop.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class Environment {
    private static final Properties properties = new Properties();
    private static String PATH_TO_PROPERTIES = "src/"
            + ((System.getProperty("properties.location") != null) ? System.getProperty("properties.location") : "main")
            + "/resources/app-"
            + ((System.getProperty("project.profile") != null) ? System.getProperty("project.profile") : "dev")
            + ".properties";

    static {
        try (FileInputStream inputStream = new FileInputStream(PATH_TO_PROPERTIES)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println();
    }

    private Environment() {
    }

    public static String getProperty(String string) {
        return properties.getProperty(string);
    }
}