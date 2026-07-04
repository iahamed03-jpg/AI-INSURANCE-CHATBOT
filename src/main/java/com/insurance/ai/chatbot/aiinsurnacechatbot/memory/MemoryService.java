package com.insurance.ai.chatbot.aiinsurnacechatbot.memory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class MemoryService {

    private final List<String> conversationHistory = new ArrayList<>();
    private final int MAX_HISTORY_SIZE = 10; 

    public void limitConversationHistory() {
        if (conversationHistory.size() > MAX_HISTORY_SIZE) {
            conversationHistory.subList(0, conversationHistory.size() - MAX_HISTORY_SIZE).clear();
        }
    }

    public void addUserMessage(String message) {
        limitConversationHistory();
        conversationHistory.add("User: " + message);
    }

    public void addAssistantMessage(String message) {
        limitConversationHistory();
        conversationHistory.add("Assistant: " + message);
    }

    public String getConversationHistory() {
        return String.join("\n", conversationHistory);
    }

    public void clearConversationHistory() {
        conversationHistory.clear();
    }

}
