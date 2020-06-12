package com.twinsdaddy.logback.chapter4_appenders.socket;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.net.SocketAppender;
import ch.qos.logback.core.util.Duration;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SocketClient1 {

    static void usage(String msg) {

    }

    // java com.twinsdaddy.logback.chapter4_appenders.socket.SocketClient1 localhost 6000
    public static void main(String[] args) throws Exception {
        if(args.length != 2) {
            usage("Wrong number of arguments");
        }

        String hostName = args[0];
        int port = Integer.parseInt(args[1]);

        // Create a SocketAppender connected to hostname:port with a
        // reconnection delay of 10000 seconds.
        SocketAppender socketAppender = new SocketAppender();
        socketAppender.setRemoteHost(hostName);
        socketAppender.setPort(port);
        socketAppender.setReconnectionDelay(new Duration(10));
        socketAppender.setQueueSize(1);
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        socketAppender.setContext(lc);

        // SocketAppender options become active only after the execution
        // of the next statement.
        socketAppender.start();

        Logger logger = (Logger) LoggerFactory.getLogger(SocketClient1.class);
        logger.addAppender(socketAppender);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Type a message to send to log server at " + hostName + ":" + port + ". Type 'q' to quit.");

            String s = reader.readLine();

            if(s.equals("q")) {
                break;
            } else {
                logger.debug(s);
            }
        }
    }
}
