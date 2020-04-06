package util.log;

import java.util.logging.Logger;

public class GameLogger {
    
    static final Logger logger = Logger.getLogger(GameLogger.class.getName());

    private GameLogger(){}

    public static void warning(String msg) {
        logger.warning(msg);
    }

    public static void interrupted() {
        warning("interrupted!");
    }

    public static void log(String msg) {
        logger.info(msg);
    }

    //only for testing
    public static void rc() {
        log("reached checkpoint!");
    }
}