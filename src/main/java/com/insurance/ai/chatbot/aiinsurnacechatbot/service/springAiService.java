package com.insurance.ai.chatbot.aiinsurnacechatbot.service;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.insurance.ai.chatbot.aiinsurnacechatbot.application.ChatRequest;
import com.insurance.ai.chatbot.aiinsurnacechatbot.application.ChatResponse;
import com.insurance.ai.chatbot.aiinsurnacechatbot.cache.ResponseCacheService;
import com.insurance.ai.chatbot.aiinsurnacechatbot.config.OllamaProperties;
import com.insurance.ai.chatbot.aiinsurnacechatbot.rag.RagService;

@Service
public class springAiService {

    private final ChatClient chatClient;
    private final RagService ragService;
    private final OllamaProperties ollamaProperties;
    private final ResponseCacheService cacheService;

    public springAiService(ChatClient chatClient,
                           RagService ragService,
                           OllamaProperties ollamaProperties,
                           ResponseCacheService cacheService) {
        this.chatClient = chatClient;
        this.ragService = ragService;
        this.ollamaProperties = ollamaProperties;
        this.cacheService = cacheService;
    }

    public ChatResponse ask(ChatRequest chatRequest) {

        if (chatRequest == null || chatRequest.getText().isBlank()) {
            throw new IllegalArgumentException("Text must not be blank");
        }

        String cachedReply = cacheService.get(chatRequest.getText());
        if (cachedReply != null) {
            return new ChatResponse(cachedReply, ollamaProperties.getModel(), System.currentTimeMillis());
        }

        String prompt = ragService.buildPrompt(chatRequest.getText());
        String reply = chatClient.prompt(prompt).call().content();
        cacheService.put(chatRequest.getText(), reply);
        return new ChatResponse(reply, ollamaProperties.getModel(), System.currentTimeMillis());
    }

}
