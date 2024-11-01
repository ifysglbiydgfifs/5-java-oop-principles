package com.example.task04;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler implements MessageHandler {
    private final File file;

    public FileHandler(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public void handleMessage(String message) {
        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            fos.write((message + "\n").getBytes());
        } catch (IOException e) {
            System.err.println("Error");
        }
    }
}