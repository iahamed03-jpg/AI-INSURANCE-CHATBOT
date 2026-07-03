package com.insurance.ai.chatbot.aiinsurnacechatbot.embedding;

import java.util.List;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.stereotype.Service;

@Service
public class EmbeddingService {
    private final EmbeddingModel embeddingModel;
    public EmbeddingService(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    public float[] embed(String texts) {
       EmbeddingResponse response = embeddingModel.embedForResponse(List.of(texts));
       return response.getResults().get(0).getOutput();
    }
}
