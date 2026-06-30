package com.insurance.ai.chatbot.aiinsurnacechatbot.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class ResponseCacheService {
    private final Map<String, String> cache = new ConcurrentHashMap<>();

    public String get(String key) {
        return cache.get(key.toLowerCase());
    }

    public void put(String key, String response) {
        cache.put(key.toLowerCase(), response);
    }
}
