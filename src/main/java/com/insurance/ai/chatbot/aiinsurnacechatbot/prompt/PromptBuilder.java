package com.insurance.ai.chatbot.aiinsurnacechatbot.prompt;

import org.springframework.stereotype.Component;

import com.insurance.ai.chatbot.aiinsurnacechatbot.memory.MemoryService;

@Component
public class PromptBuilder {
    private final MemoryService memoryService;

    public PromptBuilder(MemoryService memoryService) {
        this.memoryService = memoryService;
    }

    public String build(String question, String context) {
        String prompt = "You are an AI Inusurance assistant. \nconversation history:" 
        + memoryService.getConversationHistory() 
        + "\nContext: " + context + "\n\nQuestion: " + question;
        System.out.println("Conversation History: " + memoryService.getConversationHistory());
        System.out.println("Generated Prompt: " + prompt);
        return prompt;
    }
}
