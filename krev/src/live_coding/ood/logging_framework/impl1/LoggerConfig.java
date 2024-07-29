package live_coding.ood.logging_framework.impl1;

import live_coding.ood.logging_framework.impl1.handlers.ILoggingHandler;

public class LoggerConfig {
    private ILoggingHandler loggingHandler;
    private LogLevel logLevel;

    public LoggerConfig(ILoggingHandler loggingHandler, LogLevel logLevel) {
        this.loggingHandler = loggingHandler;
        this.logLevel = logLevel;
    }

    public ILoggingHandler getLoggingHandler() {
        return loggingHandler;
    }

    public void setLoggingHandler(ILoggingHandler loggingHandler) {
        this.loggingHandler = loggingHandler;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }
}
