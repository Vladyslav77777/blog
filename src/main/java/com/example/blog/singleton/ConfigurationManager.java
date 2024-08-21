package com.example.blog.singleton;

public class ConfigurationManager {

    private static ConfigurationManager instance;
    private String applicationName = "Simple Blog";

    private ConfigurationManager() {
        // Инициализация настроек
    }

    public static synchronized ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
