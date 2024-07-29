package live_coding.ood.logging_framework.impl1.handlers;

import live_coding.ood.logging_framework.impl1.LogMessage;

public class ConsoleLoggingHandler implements ILoggingHandler {
    @Override
    public void log(LogMessage logMessage) {
        System.out.println("PRINT TO CONSOLE: " + logMessage.toString());
    }
}
