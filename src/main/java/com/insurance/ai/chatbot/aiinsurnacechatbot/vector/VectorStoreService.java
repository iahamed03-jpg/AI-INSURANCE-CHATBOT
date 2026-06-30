package com.insurance.ai.chatbot.aiinsurnacechatbot.vector;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.insurance.ai.chatbot.aiinsurnacechatbot.embedding.EmbeddingService;

@Service
public class VectorStoreService {
    private final EmbeddingService embeddingService;

    public VectorStoreService(EmbeddingService embeddingService) {
        this.embeddingService = embeddingService;
    }

    public List<String> search(String text) {
        embeddingService.embed(text);
        return new ArrayList<>(List.of(
                "Insurance claims should be reported promptly.",
                "Coverage depends on your selected policy."
        ));
    }
}
