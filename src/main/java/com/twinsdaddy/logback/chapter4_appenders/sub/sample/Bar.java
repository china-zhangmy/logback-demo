package com.twinsdaddy.logback.chapter4_appenders.sub.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bar {
    Logger logger = LoggerFactory.getLogger(Bar.class);

    public String toString() {
        return "test 123";
    }

    public void createLoggingRequest() {
        subMethodToCreateRequest();
    }

    private void subMethodToCreateRequest() {
        logger.error("error-level request", new Exception("test exception"));
    }
}
