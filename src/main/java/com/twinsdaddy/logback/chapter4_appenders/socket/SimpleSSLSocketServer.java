package com.twinsdaddy.logback.chapter4_appenders.socket;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.net.ssl.ConfigurableSSLServerSocketFactory;
import ch.qos.logback.core.net.ssl.SSLParametersConfiguration;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;

/**
 * Copied from ch.qos.logback.classic.net.SimpleSSLSocketServer,
 * as the original class can't construct correctly itself via its super class ch.qos.logback.classic.net.SimpleSocketServer
 */

/**
 * A {@link SimpleSocketServer} that supports SSL.
 *
 * <pre>
 *      &lt;b&gt;Usage:&lt;/b&gt; java ch.qos.logback.classic.net.ssl.SimpleSSLSocketServer port configFile
 * </pre>
 *
 * where <em>port</em> is a port number where the server listens and
 * <em>configFile</em> is an xml configuration file fed to
 * {@link JoranConfigurator}.
 *
 * When running the SimpleSSLServerFactory as shown above, it is necessary to
 * configure JSSE system properties using {@code -Dname=value} on the
 * command-line when starting the server. In particular, you will probably
 * want/need to configure the following system properties:
 * <ul>
 * <li>javax.net.ssl.keyStore</li>
 * <li>javax.net.ssl.keyStorePassword</li>
 * <li>javax.net.ssl.keyStoreType</li>
 * <li>javax.net.ssl.trustStore</li>
 * <li>javax.net.ssl.trustStorePassword</li>
 * <li>javax.net.ssl.trustStoreType</li>
 * </ul>
 * <p>
 * See the <a href=
 * "http://docs.oracle.com/javase/1.5.0/docs/guide/security/jsse/JSSERefGuide.html#InstallationAndCustomization">
 * Customizing the JSSE</a> in the JSSE Reference Guide for details on how to
 * set these system properties.
 *
 * @author Carl Harris
 */
public class SimpleSSLSocketServer extends SimpleSocketServer {

    private final ServerSocketFactory socketFactory;

    public static void main(String argv[]) throws Exception {
        doMain(SimpleSSLSocketServer.class, argv);
    }

    /**
     * Creates a new server using the default SSL context.
     *
     * @param lc   logger context for received events
     * @param port port on which the server is to listen
     * @throws NoSuchAlgorithmException if the default SSL context cannot be
     *                                  created
     */
    public SimpleSSLSocketServer(LoggerContext lc, int port) throws NoSuchAlgorithmException {
        this(lc, port, SSLContext.getDefault());
    }

    /**
     * Creates a new server using a custom SSL context.
     *
     * @param lc         logger context for received events
     * @param port       port on which the server is to listen
     * @param sslContext custom SSL context
     */
    public SimpleSSLSocketServer(LoggerContext lc, int port, SSLContext sslContext) {
        super(lc, port);
        if (sslContext == null) {
            throw new NullPointerException("SSL context required");
        }
        SSLParametersConfiguration parameters = new SSLParametersConfiguration();

        parameters.setContext(lc);
        this.socketFactory = new ConfigurableSSLServerSocketFactory(parameters, sslContext.getServerSocketFactory());
    }

    @Override
    protected ServerSocketFactory getServerSocketFactory() {
        return socketFactory;
    }

    static int parsePortNumber(String portStr) {
        try {
            return Integer.parseInt(portStr);
        } catch (java.lang.NumberFormatException e) {
            e.printStackTrace();
            usage("Could not interpret port number [" + portStr + "].");
            // we won't get here
            return -1;
        }
    }

    static void usage(String msg) {
        System.err.println(msg);
        System.err.println("Usage: java " + SimpleSocketServer.class.getName() + " port configFile");
        System.exit(1);
    }
}
