package com.twinsdaddy.logback.chapter3_configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Except code that configures logback (if such code exists) client code does not need to depend on logback.
 * Applications that use logback as their logging framework will have a compile-time dependency on SLF4J but not logback.
 */
public class MyApp1 {

    final static Logger logger = LoggerFactory.getLogger(MyApp1.class);

    public static void main(String[] args) {
        logger.info("Entering application.");

        Foo foo = new Foo();
        foo.doIt();
        logger.info("Exiting application.");
    }
}
