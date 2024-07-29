package live_coding.ood.logging_framework.impl1.handlers;

import live_coding.ood.logging_framework.impl1.LogMessage;

public class DatabaseLoggingHandler implements ILoggingHandler {
    @Override
    public void log(LogMessage logMessage) {
        System.out.println("PRINT TO DATABASE: " + logMessage.toString());
    }
}
