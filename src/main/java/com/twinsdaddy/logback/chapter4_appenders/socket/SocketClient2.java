package com.twinsdaddy.logback.chapter4_appenders.socket;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SocketClient2 {

    static void usage(String msg) {
        System.err.println(msg);
        System.err.println("Usage: java " + SocketClient1.class.getName() + " hostname port\n" + "   hostname the name of the remote log server\n"
                + "   port (integer) the port number of the server\n");
        System.exit(1);
    }

    // java -Dhost=localhost -Dport=6000 -DincludeCallerData=false com.twinsdaddy.logback.chapter4_appenders.socket.SocketClient2 target/classes/chapter4_appenders/socket/client1.xml
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            usage("Wrong number of arguments.");
        }

        String configFile = args[0];

        if(configFile.endsWith(".xml")) {
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
            JoranConfigurator configurator = new JoranConfigurator();
            lc.stop();
            configurator.setContext(lc);
            configurator.doConfigure(configFile);
        }

        Logger logger = LoggerFactory.getLogger(SocketClient2.class);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Type a message to send to log server. Type 'q' to quit.");

            String s = reader.readLine();

            if (s.equals("q")) {
                break;
            } else {
                logger.debug(s);
            }
        }
    }
}
