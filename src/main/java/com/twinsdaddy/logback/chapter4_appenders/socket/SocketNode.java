package com.twinsdaddy.logback.chapter4_appenders.socket;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.net.server.HardenedLoggingEventInputStream;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Read {@link ILoggingEvent} objects sent from a remote client using Sockets
 * (TCP). These logging events are logged according to local policy, as if they
 * were generated locally.
 *
 * <p>
 * For example, the socket node might decide to log events to a local file and
 * also resent them to a second socket node.
 *
 * @author Ceki G&uuml;lc&uuml;
 * @author S&eacute;bastien Pennec
 *
 * @since 0.8.4
 */
public class SocketNode implements Runnable {

    Socket socket;
    LoggerContext context;
    HardenedLoggingEventInputStream hardenedLoggingEventInputStream;
    SocketAddress remoteSocketAddress;

    Logger logger;
    boolean closed = false;
    SimpleSocketServer socketServer;

    public SocketNode(SimpleSocketServer socketServer, Socket socket, LoggerContext context) {
        this.socketServer = socketServer;
        this.socket = socket;
        remoteSocketAddress = socket.getRemoteSocketAddress();
        this.context = context;
        logger = context.getLogger(ch.qos.logback.classic.net.SocketNode.class);
    }

    // public
    // void finalize() {
    // System.err.println("-------------------------Finalize called");
    // System.err.flush();
    // }

    public void run() {

        try {
            hardenedLoggingEventInputStream = new HardenedLoggingEventInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (Exception e) {
            logger.error("Could not open ObjectInputStream to " + socket, e);
            closed = true;
        }

        ILoggingEvent event;
        Logger remoteLogger;

        try {
            while (!closed) {
                // read an event from the wire
                event = (ILoggingEvent) hardenedLoggingEventInputStream.readObject();
                // get a logger from the hierarchy. The name of the logger is taken to
                // be the name contained in the event.
                remoteLogger = context.getLogger(event.getLoggerName());
                // apply the logger-level filter
                if (remoteLogger.isEnabledFor(event.getLevel())) {
                    // finally log the event as if was generated locally
                    remoteLogger.callAppenders(event);
                }
            }
        } catch (java.io.EOFException e) {
            logger.info("Caught java.io.EOFException closing connection.");
        } catch (java.net.SocketException e) {
            logger.info("Caught java.net.SocketException closing connection.");
        } catch (IOException e) {
            logger.info("Caught java.io.IOException: " + e);
            logger.info("Closing connection.");
        } catch (Exception e) {
            logger.error("Unexpected exception. Closing connection.", e);
        }

        socketServer.socketNodeClosing(this);
        close();
    }

    void close() {
        if (closed) {
            return;
        }
        closed = true;
        if (hardenedLoggingEventInputStream != null) {
            try {
                hardenedLoggingEventInputStream.close();
            } catch (IOException e) {
                logger.warn("Could not close connection.", e);
            } finally {
                hardenedLoggingEventInputStream = null;
            }
        }
    }

    @Override
    public String toString() {
        return this.getClass().getName() + remoteSocketAddress.toString();
    }
}
