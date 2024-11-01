package com.example.task04;

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

    Logger(String name) {
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

    public String debug(String message) {
        return log(Level.DEBUG, message);
    }

    public String info(String message) {
        return log(Level.INFO, message);
    }

    public String warning(String message) {
        return log(Level.WARNING, message);
    }

    public String error(String message) {
        return log(Level.ERROR, message);
    }

    public String debug(String message, Object... args) {
        return log(Level.DEBUG, message, args);
    }

    public String info(String message, Object... args) {
        return log(Level.INFO, message, args);
    }

    public String warning(String message, Object... args) {
        return log(Level.WARNING, message, args);
    }

    public String error(String message, Object... args) {
        return log(Level.ERROR, message, args);
    }

    private String log(Level level, String message) {
        if (level.ordinal() >= currentLevel.ordinal()) {
            return printLog(level, message);
        }
        return null;
    }

    private String log(Level level, String message, Object... args) {
        if (level.ordinal() >= currentLevel.ordinal()) {
            String formattedMessage = MessageFormat.format(message, args);
            return printLog(level, formattedMessage);
        }
        return null;
    }

    private String printLog(Level level, String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");

        String logMessage = String.format("[%s] %s %s %s - %s",
                level.name(),
                now.format(date),
                now.format(time),
                name,
                message);
        return logMessage;
    }
}

