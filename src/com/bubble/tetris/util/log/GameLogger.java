package com.bubble.tetris.util.log;

import java.util.logging.Logger;

public class GameLogger {
    
    static final Logger logger = Logger.getLogger(GameLogger.class.getName());
    private static final boolean DEBUG_MODE = false;
    private GameLogger(){}

    private static void resetColor() {
        log(Colors.RESET);
    }

    private static void setColor(Color c) {
        log(c.get());
    }

    public static void warning(String msg) {
        setColor(Color.CYAN);
        logger.warning(msg);
        resetColor();
    }

    public static void interrupted() {
        warning("interrupted!");
    }

    public static void log(String msg) {
        logger.info(msg);
    }

    public static void error(Exception ex, Object from) {
        warning(ex.toString() + " from " + from.getClass().getName());
    }

    public static void info(String msg) {
        setColor(Color.BLUE);
        logger.info(msg);
        resetColor();
    }

    public static void debug(String msg) {
        if (DEBUG_MODE) logger.warning(msg);
        else logger.finest(msg);
    }

    public static void outdatedLog(String msg) {
        logger.finest(msg);
    }

    //only for testing
    public static void rc() {
        log("reached checkpoint!");
    }

    private enum Color {
        RED(Colors.RED), BLUE(Colors.BLUE), GREEN(Colors.GREEN), CYAN(Colors.CYAN), RESET(Colors.RESET);
        
        private final String c;

        private Color(String color) {
            this.c = color;
        }

        public String get() {
            return this.c;
        }
    }
    
    private class Colors {
        public static final String RESET = "\u001B[0m";
        public static final String BLUE = "\u001B[34m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String CYAN = "\u001B[36m";
    }
}