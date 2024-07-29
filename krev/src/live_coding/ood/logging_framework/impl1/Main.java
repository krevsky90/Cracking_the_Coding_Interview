package live_coding.ood.logging_framework.impl1;

import live_coding.ood.logging_framework.impl1.handlers.DatabaseLoggingHandler;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.debug("debug message");
        logger.info("info message");
        logger.error("error message");
        logger.fatal("fatal message");

        LoggerConfig config = new LoggerConfig(new DatabaseLoggingHandler(), LogLevel.ERROR);
        logger.setLoggingHandler(config);
        logger.debug("debug message");
        logger.info("info message");
        logger.error("error message");
        logger.fatal("fatal message");
    }
}
