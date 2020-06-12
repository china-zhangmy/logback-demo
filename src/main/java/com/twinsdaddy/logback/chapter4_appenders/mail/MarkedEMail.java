package com.twinsdaddy.logback.chapter4_appenders.mail;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class MarkedEMail {

    static void usage(String msg) {
        System.err.println(msg);
        System.err.println("Usage: java " + MarkedEMail.class.getName() + " configFile\n" + "   configFile a logback configuration file in XML format.");
        System.exit(1);
    }

    public static void main(String[] args) throws JoranException {
        if (args.length != 1) {
            usage("Wrong number of arguments.");
        }

        String configFile = args[0];

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        lc.reset();
        configurator.setContext(lc);
        configurator.doConfigure(configFile);
        StatusPrinter.printInCaseOfErrorsOrWarnings(lc);

        Logger logger = LoggerFactory.getLogger(MarkedEMail.class);

        int runLength = 100;
        for (int i = 1; i <= runLength; i++) {
            if ((i % 10) < 9) {
                logger.debug("This is a debug message. Message number: " + i);
            } else {
                logger.error("This is an error message. Message number: " + i);
            }
        }

        Marker notifyAdmin = MarkerFactory.getMarker("NOTIFY_ADMIN");
        logger.error(notifyAdmin, "This is a serious an error requiring the admin's attention", new Exception("Just testing"));

        StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
    }
}
