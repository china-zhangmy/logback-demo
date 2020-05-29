/**
 * Here is a list of the three required steps in order to enable logging in your application.
 *
 *     Configure the logback environment. You can do so in several more or less sophisticated ways. More on this later.
 *     In every class where you wish to perform logging, retrieve a Logger instance by invoking the org.slf4j.LoggerFactory class' getLogger() method, passing the current class name or the class itself as a parameter.
 *     Use this logger instance by invoking its printing methods, namely the debug(), info(), warn() and error() methods. This will produce logging output on the configured appenders.
 */
package com.twinsdaddy.logback.chapter1_introduction;