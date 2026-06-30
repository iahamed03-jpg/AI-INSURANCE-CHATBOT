package com.insurance.ai.chatbot.aiinsurnacechatbot.embedding;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmbeddingService {
    public List<Double> embed(String text) {
        return List.of(0.0, 0.0, 0.0);
    }
}
