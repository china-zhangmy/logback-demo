package com.twinsdaddy.logback.chapter3_configuration;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

public class PackagingData {

    public static void main(String[] args) {
        // As of version 1.1.4, packaging data is disabled by default.
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        lc.setPackagingDataEnabled(true);
    }
}
