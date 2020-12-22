package logger;

import org.apache.log4j.Priority;

public class Logger {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Logger.class);

    private Logger() {

    }

    public static void debug(String message) {
        logger.log(Priority.DEBUG, message);
    }

    public static void step(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.log(Priority.ERROR, message);
    }

    public static void info(String message) { logger.info(message);}
}
