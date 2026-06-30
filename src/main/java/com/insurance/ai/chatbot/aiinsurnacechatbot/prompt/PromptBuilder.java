package com.insurance.ai.chatbot.aiinsurnacechatbot.prompt;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {
    public String build(String question, String context) {
        return "You are an insurance support assistant.\nContext:\n" + context + "\n\nQuestion: " + question;
    }
}
