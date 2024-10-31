package com.example.task01;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Logger {

    public enum Level {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    private static final Map<String, Logger> loggers = new HashMap<>();
    private final String name;
    private Level currentLevel = Level.DEBUG;

    private Logger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name) {
        if (!loggers.containsKey(name)) {
            loggers.put(name, new Logger(name));
        }
        return loggers.get(name);
    }

    public String getName() {
        return name;
    }

    public void setLevel(Level level) {
        this.currentLevel = level;
    }

    public Level getLevel() {
        return currentLevel;
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void debug(String message, Object... args) {
        log(Level.DEBUG, message, args);
    }

    public void info(String message, Object... args) {
        log(Level.INFO, message, args);
    }

    public void warning(String message, Object... args) {
        log(Level.WARNING, message, args);
    }

    public void error(String message, Object... args) {
        log(Level.ERROR, message, args);
    }

    private void log(Level level, String message) {
        if (level.ordinal() >= currentLevel.ordinal()) {
            printLog(level, message);
        }
    }

    private void log(Level level, String message, Object... args) {
        if (level.ordinal() >= currentLevel.ordinal()) {
            String formattedMessage = MessageFormat.format(message, args);
            printLog(level, formattedMessage);
        }
    }

    private void printLog(Level level, String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");

        String logMessage = String.format("[%s] %s %s %s - %s",
                level.name(),
                now.format(date),
                now.format(time),
                name,
                message);
        System.out.println(logMessage);
    }
}

