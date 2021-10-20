package io.github.erobus1.internal;

public class Logger {
    private static final String RESET = "\u001B[0m";


    public static String BLACK(String text) {
        return "\u001B[30m" + text + RESET;
    }
    public static String RED(String text) {
        return "\u001B[31m" + text + RESET;
    }
    public static String GREEN(String text) {
        return "\u001B[32m" + text + RESET;
    }
    public static String YELLOW(String text) {
        return "\u001B[33m" + text + RESET;
    }
    public static String BLUE(String text) {
        return "\u001B[34m" + text + RESET;
    }
    public static String PURPLE(String text) {
        return "\u001B[35m" + text + RESET;
    }
    public static String CYAN(String text) {
        return "\u001B[36m" + text + RESET;
    }
    public static String WHITE(String text) {
        return "\u001B[37m" + text + RESET;
    }
}
