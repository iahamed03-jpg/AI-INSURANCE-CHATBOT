package com.insurance.ai.chatbot.aiinsurnacechatbot.document;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    public List<String> loadDocuments() {
        return List.of(
                "Policyholders can file claims online or through customer support.",
                "Coverage includes accidental damage, theft, and medical emergencies depending on product.",
                "Customers should keep receipts and incident reports for faster claims processing."
        );
    }
}
