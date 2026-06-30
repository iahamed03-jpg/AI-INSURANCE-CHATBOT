package com.insurance.ai.chatbot.aiinsurnacechatbot.rag;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.insurance.ai.chatbot.aiinsurnacechatbot.document.DocumentService;
import com.insurance.ai.chatbot.aiinsurnacechatbot.prompt.PromptBuilder;
import com.insurance.ai.chatbot.aiinsurnacechatbot.vector.VectorStoreService;

@Service
public class RagService {
    private final DocumentService documentService;
    private final VectorStoreService vectorStoreService;
    private final PromptBuilder promptBuilder;

    public RagService(DocumentService documentService,
                      VectorStoreService vectorStoreService,
                      PromptBuilder promptBuilder) {
        this.documentService = documentService;
        this.vectorStoreService = vectorStoreService;
        this.promptBuilder = promptBuilder;
    }

    public String buildPrompt(String userText) {
        List<String> documents = documentService.loadDocuments();
        List<String> vectorMatches = vectorStoreService.search(userText);
        String context = Stream.concat(documents.stream(), vectorMatches.stream())
                .distinct()
                .collect(Collectors.joining("\n"));

        return promptBuilder.build(userText, context);
    }
}
