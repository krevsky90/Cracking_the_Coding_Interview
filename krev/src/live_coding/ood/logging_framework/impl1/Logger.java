package live_coding.ood.logging_framework.impl1;

import live_coding.ood.logging_framework.impl1.handlers.ConsoleLoggingHandler;

public class Logger {
    private static Logger instance;

    private LoggerConfig loggerConfig;

    private Logger() {
        loggerConfig = new LoggerConfig(new ConsoleLoggingHandler(), LogLevel.INFO);
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void setLoggingHandler(LoggerConfig config) {
        this.loggerConfig = config;
    }

    public void log(LogLevel logLevel, String message) {
        if (logLevel.ordinal() >= loggerConfig.getLogLevel().ordinal()) {
            LogMessage logMessage = new LogMessage(message, logLevel);
            loggerConfig.getLoggingHandler().log(logMessage);
        }
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }
}
