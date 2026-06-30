package com.insurance.ai.chatbot.aiinsurnacechatbot.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.insurance.ai.chatbot.aiinsurnacechatbot.application.ChatRequest;
import com.insurance.ai.chatbot.aiinsurnacechatbot.application.ChatResponse;
import com.insurance.ai.chatbot.aiinsurnacechatbot.cache.ResponseCacheService;
import com.insurance.ai.chatbot.aiinsurnacechatbot.config.OllamaProperties;
import com.insurance.ai.chatbot.aiinsurnacechatbot.rag.RagService;

import tools.jackson.databind.ObjectMapper;

@Service
public class ChatService {
    private final RagService ragService;
    private final RestTemplate restTemplate;
    private final OllamaProperties ollamaProperties;
    private final ResponseCacheService cacheService;

    public ChatService(RagService ragService,
                       RestTemplate restTemplate,
                       OllamaProperties ollamaProperties,
                       ResponseCacheService cacheService) {
        this.ragService = ragService;
        this.restTemplate = restTemplate;
        this.ollamaProperties = ollamaProperties;
        this.cacheService = cacheService;
    }

    public ChatResponse ask(ChatRequest request) {
        String userText = request.getText();
        if (userText == null || userText.isBlank()) {
            userText = request.getMessage();
        }
        if (userText == null || userText.isBlank()) {
            throw new IllegalArgumentException("Text must not be blank");
        }

        String cachedReply = cacheService.get(userText);
        if (cachedReply != null) {
            return new ChatResponse(cachedReply, ollamaProperties.getModel(), System.currentTimeMillis());
        }

        String prompt = ragService.buildPrompt(userText);
        String endpoint = ollamaProperties.getBaseUrl().replaceAll("/$", "") + "/api/generate";
        System.out.println("Sending request to Ollama at: " + ollamaProperties.getBaseUrl() + " with model: " + ollamaProperties.getModel());
        OllamaRequest ollamaRequest = new OllamaRequest(ollamaProperties.getModel(), prompt, false);

        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("Request: " + mapper.writeValueAsString(ollamaRequest));
            ResponseEntity<OllamaResponse> response = restTemplate.postForEntity(endpoint, ollamaRequest, OllamaResponse.class);
            String reply = response.getBody() != null && response.getBody().getResponse() != null
                    ? response.getBody().getResponse()
                    : "I could not generate a response from Ollama.";
            cacheService.put(userText, reply);
            return new ChatResponse(reply, ollamaProperties.getModel(), System.currentTimeMillis());
        } catch (Exception ex) {
            String fallbackReply = "Ollama is currently unavailable. Please verify the service at " + endpoint + ".";
            cacheService.put(userText, fallbackReply);
            return new ChatResponse(fallbackReply, ollamaProperties.getModel(), System.currentTimeMillis());
        }
    }

    private static class OllamaRequest {
        private final String model;
        private final String prompt;
        private final boolean stream;

        private OllamaRequest(String model, String prompt, boolean stream) {
            this.model = model;
            this.prompt = prompt;
            this.stream = stream;
        }

        public String getModel() {
            return model;
        }
        public String getPrompt() {
            return prompt;
        }
        public boolean isStream() {
            return stream;
        }
    }

    private static class OllamaResponse {
        private String response;

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }
    }
}
