package com.twinsdaddy.logback.chapter3_configuration;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;

/**
 * If warning or errors occur during the parsing of the configuration file,
 * logback will automatically print its internal status messages on the console.
 */
public class MyApp2 {

    public static void main(String[] args) {
        // assume SLF4J is bound to logback in the current environment
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        // print logback's internal status
        StatusPrinter.print(lc);
    }
}
