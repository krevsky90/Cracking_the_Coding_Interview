package live_coding.ood.logging_framework.impl1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogMessage {
    private final String message;
    private final LogLevel logLevel;
    private final LocalDateTime dateTime;

    public LogMessage(String message, LogLevel logLevel) {
        this.message = message;
        this.logLevel = logLevel;
        this.dateTime = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public LocalDateTime getTimestamp() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "[" + logLevel + "] " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + " - " + message;
    }
}
