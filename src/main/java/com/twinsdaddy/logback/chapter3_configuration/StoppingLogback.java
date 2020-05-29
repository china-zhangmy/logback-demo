package com.twinsdaddy.logback.chapter3_configuration;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

/**
 * In order to release the resources used by logback-classic, it is always a good idea to stop the logback context.
 * Stopping the context will close all appenders attached to loggers defined by the context and stop any active threads in an orderly way.
 */
public class StoppingLogback {

    public static void main(String[] args) {
        // assume SLF4J is bound to logback-classic in the current environment
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        lc.stop();
        // In web-applications the above code could be invoked from within the contextDestroyed method of ServletContextListener in order to stop logback-classic and release resources.
        // Starting with version 1.1.10, the appropriate ServletContextListener is installed automatically

        // another way: Stopping logback-classic via a shutdown hook <shutdownHook/>
    }
}
