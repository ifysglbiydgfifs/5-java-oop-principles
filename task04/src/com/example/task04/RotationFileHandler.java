package com.example.task04;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private final String file;
    private final ChronoUnit rotationUnit;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");

    public RotationFileHandler(String filePath, ChronoUnit rotationUnit) {
        this.file = filePath;
        this.rotationUnit = rotationUnit;
    }

    @Override
    public void handleMessage(String logMessage) {
        String formattedTime = LocalDateTime.now().truncatedTo(rotationUnit).format(formatter);
        String newFile = file + "_" + formattedTime + ".txt";

        try (FileOutputStream fos = new FileOutputStream(newFile, true)) {
            fos.write((logMessage + "\n").getBytes());
        } catch (IOException e) {
            System.err.println("Error writing to file: " + newFile);
        }
    }
}
