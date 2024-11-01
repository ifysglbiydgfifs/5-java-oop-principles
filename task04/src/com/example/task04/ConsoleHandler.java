package com.example.task04;

public class ConsoleHandler implements MessageHandler {

    @Override
    public void handleMessage(String logMessage) {
        if (logMessage != null) {
            System.out.println(logMessage);
        }
    }
}