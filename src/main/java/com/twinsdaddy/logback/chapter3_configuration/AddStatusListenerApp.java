package com.twinsdaddy.logback.chapter3_configuration;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.status.OnConsoleStatusListener;
import ch.qos.logback.core.status.StatusManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddStatusListenerApp {

    public static void main(String[] args) {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusManager statusManager = lc.getStatusManager();
        OnConsoleStatusListener onConsoleStatusListener = new OnConsoleStatusListener();
        statusManager.add(onConsoleStatusListener);

        // another way: java -Dlogback.statusListenerClass=ch.qos.logback.core.status.OnConsoleStatusListener MainClass

        Logger logger = LoggerFactory.getLogger("myApp");
        logger.info("Entering Application.");

        Foo foo = new Foo();
        foo.doIt();
        logger.info("Exiting application.");
    }
}
