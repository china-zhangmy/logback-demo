package com.twinsdaddy.logback.chapter4_appenders.mail;

import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluator;
import ch.qos.logback.core.spi.ContextAwareBase;

/**
 * If the Evaluator property is not set, the SMTPAppender defaults to an OnErrorEvaluator instance which triggers email transmission when it encounters an event of level ERROR.
 */
public class CounterBasedEvaluator extends ContextAwareBase implements EventEvaluator {

    static int LIMIT = 1024;
    int counter = 0;
    String name;
    boolean started;

    @Override
    public boolean evaluate(Object event) throws NullPointerException, EvaluationException {
        counter++;

        if(counter == LIMIT) {
            counter = 0;

            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void start() {
        started = true;
    }

    @Override
    public void stop() {
        started = false;
    }

    @Override
    public boolean isStarted() {
        return started;
    }
}
