package com.twinsdaddy.logback.chapter1_introduction;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logback can report information about its internal state using a built-in status system.
 * Important events occurring during logback's lifetime can be accessed through a component called StatusManager.
 */
public class HelloWorld2 {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(HelloWorld2.class);
        logger.debug("Hello world.");

        // print internal state
        LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
//        StatusManager sm = lc.getStatusManager();
        StatusPrinter.print(lc);
    }
}
