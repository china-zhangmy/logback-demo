package com.twinsdaddy.logback.chapter4_appenders.mail;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EMail {

    static void usage(String msg) {
        System.err.println(msg);
        System.err.println("Usage: java " + EMail.class.getName() + " runLength configFile\n" + "   runLength (integer) the number of logs to generate\n"
                + "   configFile a logback configuration file in XML format." + " XML files must have a '.xml' extension.");
        System.exit(1);
    }

    public static void main(String[] args) throws JoranException {
        if(args.length != 2) {
            usage("Wrong number of arguments.");
        }

        int runLength = Integer.parseInt(args[0]);
        String configFile = args[1];

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        lc.reset();
        configurator.setContext(lc);
        configurator.doConfigure(configFile);

        Logger logger = LoggerFactory.getLogger(EMail.class);

        for(int i = 1; i <= runLength; i++) {
            if(i %10 < 9) {
                logger.debug("This is a debug message. Message number: " + i);
            } else {
                logger.warn("This is a warning message. Message number: " + i);
            }
        }

        logger.error("At last an error.", new Exception("Just testing"));

        lc.stop();

        StatusPrinter.printInCaseOfErrorsOrWarnings(lc);

    }
}
