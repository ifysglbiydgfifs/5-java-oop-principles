package com.example.task04;

import java.time.temporal.ChronoUnit;

public class Task04Main {
    public static void main(String[] args) {
        Logger logger = new Logger("testLogger");

        logger.setLevel(Logger.Level.INFO);

        String debugMessage = logger.debug("This is a debug message");  // Не будет выведено
        String infoMessage = logger.info("This is an info message");   // Будет выведено
        String warningMessage = logger.warning("Warning: {0} exceeded threshold", 100);  // Будет выведено
        String errorMessage = logger.error("Error occurred: {0}", "Disk failure");     // Будет выведено

        System.out.println(debugMessage);
        System.out.println(infoMessage);
        System.out.println(warningMessage);
        System.out.println(errorMessage);

        System.out.println();

        MessageHandler consoleHandler = new ConsoleHandler();
        consoleHandler.handleMessage(infoMessage);

        MessageHandler fileHandler = new FileHandler("D:\\JavaProjects\\logs\\fileHandler.txt");
        fileHandler.handleMessage(infoMessage);

        MessageHandler rotationFileHandler = new RotationFileHandler("D:\\JavaProjects\\logs\\RotationFileHandler", ChronoUnit.SECONDS);
        rotationFileHandler.handleMessage(infoMessage);

        System.out.println();

        MessageHandler memoryHandler = new MemoryHandler(2, consoleHandler);
        memoryHandler.handleMessage(infoMessage);
        memoryHandler.handleMessage(warningMessage);
        memoryHandler.handleMessage(errorMessage);

        MessageHandler rotationFileHandler1 = new RotationFileHandler("D:\\JavaProjects\\logs\\RotationFileHandler1", ChronoUnit.MINUTES);
        MessageHandler memoryHandler1 = new MemoryHandler(10, rotationFileHandler1);
        memoryHandler1.handleMessage(warningMessage);
    }
}
