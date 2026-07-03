package com.insurance.ai.chatbot.aiinsurnacechatbot.vector;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.insurance.ai.chatbot.aiinsurnacechatbot.document.DocumentService;
import com.insurance.ai.chatbot.aiinsurnacechatbot.embedding.EmbeddingService;

import jakarta.annotation.PostConstruct;

@Service
public class VectorStoreService {
    private final List<VectorDocument> vectorStore = new ArrayList<>();
    private final EmbeddingService embeddingService;
    private final DocumentService documentService;
    public VectorStoreService(EmbeddingService embeddingService, DocumentService documentService) {
        this.embeddingService = embeddingService;
        this.documentService = documentService;
    }
    @PostConstruct
    public void load(){
        List<String> documents = documentService.loadDocuments();
        int id = 1;
        for (String doc : documents) {
            float[] embedding = embeddingService.embed(doc);
            vectorStore.add(new VectorDocument(String.valueOf(id), doc, embedding));
            id++;
            System.out.println("Loaded document: " + doc + " with embedding: " + embedding.length);
        }
        System.out.println("Loaded " + vectorStore.size() + " documents into the vector store.");
    }

    public List<String> search(String question){
        float[] questionEmbedding = embeddingService.embed(question);
        return vectorStore.stream()
                .sorted((doc1, doc2) -> Double.compare(CosineSimilarity.calculate(questionEmbedding, doc2.getEmbedding()), CosineSimilarity.calculate(questionEmbedding, doc1.getEmbedding())))
                .limit(3)
                .map(VectorDocument::getText)
                .toList();
    }
}
