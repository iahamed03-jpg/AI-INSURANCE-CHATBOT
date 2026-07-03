package com.insurance.ai.chatbot.aiinsurnacechatbot.document;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ChunkingService {

    private static final int CHUNK_SIZE = 500;
    private static final int OVERLAP_SIZE = 100;

    public List<String> chunkText(String text) {
        List<String> chunks = new java.util.ArrayList<>();
        int start = 0;
        if(text == null || text.isEmpty()) {
            return Collections.emptyList();
        }
        while (start < text.length()) {
            int end = Math.min(start + CHUNK_SIZE, text.length());
            String chunk = text.substring(start, end);
            chunks.add(chunk);
            start += CHUNK_SIZE - OVERLAP_SIZE; 
        }
        return chunks;
    }

}
