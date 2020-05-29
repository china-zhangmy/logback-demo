package com.twinsdaddy.logback.chapter3_configuration;

import ch.qos.logback.classic.util.ContextInitializer;

public class SetConfigFileProperty {

    public static void main(String[] args) {
        // must be set before the first call to  LoggerFactory.getLogger();
        // ContextInitializer.CONFIG_FILE_PROPERTY is set to "logback.configurationFile"
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "path/to/config.xml");

        // another way
        // java -Dlogback.configurationFile=/path/to/config.xml MainClass
    }
}
