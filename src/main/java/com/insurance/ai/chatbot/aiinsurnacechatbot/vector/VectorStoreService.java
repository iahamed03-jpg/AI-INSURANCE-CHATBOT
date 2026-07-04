package com.insurance.ai.chatbot.aiinsurnacechatbot.vector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.insurance.ai.chatbot.aiinsurnacechatbot.embedding.EmbeddingService;

@Service
public class VectorStoreService {
    private final List<VectorDocument> vectorStore = new ArrayList<>();
    private final EmbeddingService embeddingService;
    private AtomicInteger idCounter = new AtomicInteger();
    public VectorStoreService(EmbeddingService embeddingService) {
        this.embeddingService = embeddingService;
    }
    /*@PostConstruct
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
    }*/

    public void addDocument(String text) {
        float[] embedding = embeddingService.embed(text);
        String id = String.valueOf(idCounter.incrementAndGet());
        vectorStore.add(new VectorDocument(id, text, embedding));
        //System.out.println("Added document: " + text + " with embedding: " + embedding.length);
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
