package com.insurance.ai.chatbot.aiinsurnacechatbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.insurance.ai.chatbot.aiinsurnacechatbot.document.DocumentService;

@RestController
@RequestMapping("/api/document")
public class DocumentController {
    private final DocumentService documentService;
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file) {
        try {
            documentService.process(file);
            return ResponseEntity.ok("Document uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload document: " + e.getMessage());
        }
    }
}
