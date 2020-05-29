package com.twinsdaddy.logback.chapter2_architecture;

import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Basic Selection Rule
 *      A log request of level p issued to a logger having an effective level q, is enabled if p >= q.
 *
 *      TRACE < DEBUG < INFO <  WARN < ERROR
 *
 * Appender Additivity
 *      The output of a log statement of logger L will go to all the appenders in L and its ancestors. This is the meaning of the term "appender additivity".
 *      However, if an ancestor of logger L, say P, has the additivity flag set to false, then L's output will be directed to all the appenders in L and its ancestors up to and including P but not the appenders in any of the ancestors of P.
 *      Loggers have their additivity flag set to true by default.
 */
public class LoggerSelectionRule {

    public static void main(String[] args) {
        // get a logger instance named "com.foo". Let us further assume that the
        // logger is of type  ch.qos.logback.classic.Logger so that we can
        // set its level
        ch.qos.logback.classic.Logger logger =
                (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.foo");
        //set its Level to INFO. The setLevel() method requires a logback logger
        logger.setLevel(Level.INFO);

        Logger barlogger = LoggerFactory.getLogger("com.foo.Bar");

        // This request is enabled, because WARN >= INFO
        logger.warn("Low fuel level.");

        // This request is disabled, because DEBUG < INFO.
        logger.debug("Starting search for nearest gas station.");

        // The logger instance barlogger, named "com.foo.Bar",
        // will inherit its level from the logger named
        // "com.foo" Thus, the following request is enabled
        // because INFO >= INFO.
        barlogger.info("Located nearest gas station.");

        // This request is disabled, because DEBUG < INFO.
        barlogger.debug("Exiting gas station search");
    }
}
