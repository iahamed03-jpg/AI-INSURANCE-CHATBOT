package com.insurance.ai.chatbot.aiinsurnacechatbot.document;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.insurance.ai.chatbot.aiinsurnacechatbot.vector.VectorStoreService;

@Service
public class DocumentService {

    private final PdfParserService pdfParserService;
    private final ChunkingService chunkingService;
    private final VectorStoreService vectorStoreService;

    public DocumentService(PdfParserService pdfParserService, ChunkingService chunkingService, VectorStoreService vectorStoreService) {
        this.pdfParserService = pdfParserService;
        this.chunkingService = chunkingService;
        this.vectorStoreService = vectorStoreService;
    }

    public List<String> loadDocuments() {
        return List.of(
                "Policyholders can file claims online or through customer support.",
                "Coverage includes accidental damage, theft, and medical emergencies depending on product.",
                "Customers should keep receipts and incident reports for faster claims processing."
        );
    }

    public void process(MultipartFile file) {
        try {
            String extractedText = pdfParserService.extractText(file);
            System.out.println("Extracted text from PDF: " + extractedText);
            List<String> chunks = chunkingService.chunkText(extractedText);
            System.out.println("Document chunks: " + chunks);
            for (String chunk : chunks) {
                vectorStoreService.addDocument(chunk);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to process uploaded file", e);
        }
    }
}
