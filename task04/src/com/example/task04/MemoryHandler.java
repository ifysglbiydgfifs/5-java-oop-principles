package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {
    private final List<String> messages = new ArrayList<>();
    private final int threshold;
    private final MessageHandler nextHandler;

    public MemoryHandler(int threshold, MessageHandler nextHandler) {
        this.threshold = threshold;
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleMessage(String logMessage) {
        messages.add(logMessage);

        if (messages.size() >= threshold) {
            for (String msg : messages) {
                nextHandler.handleMessage(msg);
            }
            messages.clear();
        }
    }
}