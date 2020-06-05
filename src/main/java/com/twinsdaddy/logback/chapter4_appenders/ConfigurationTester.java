package com.twinsdaddy.logback.chapter4_appenders;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.twinsdaddy.logback.chapter4_appenders.sub.sample.Bar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class ConfigurationTester {

    public static void main(String[] args) throws InterruptedException {
        Logger logger = (Logger) LoggerFactory.getLogger(ConfigurationTester.class);
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        try{
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(lc);
            lc.reset();
            configurator.doConfigure(args[0]);
        } catch (JoranException je) {
            je.printStackTrace();
        }

        logger.debug("**Hello {}", new Bar());
        MDC.put("testKey", "testValueFromMDC");
        MDC.put("testKey2", "value2");
        for(int i = 0; i< 64; i++) {
            logger.debug("logging statement {}", i);
            Thread.sleep(100);
        }
        Bar bar = new Bar();
        bar.createLoggingRequest();
    }
}
