package com.insurance.ai.chatbot.aiinsurnacechatbot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.ai.chatbot.aiinsurnacechatbot.application.ChatRequest;
import com.insurance.ai.chatbot.aiinsurnacechatbot.application.ChatResponse;
import com.insurance.ai.chatbot.aiinsurnacechatbot.service.ChatService;
import com.insurance.ai.chatbot.aiinsurnacechatbot.service.springAiService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;
    private final springAiService springAiService;

    public ChatController(ChatService chatService, springAiService springAiService) {
        this.chatService = chatService;
        this.springAiService = springAiService;
    }

    @PostMapping("/message")
    public ChatResponse sendMessage(@RequestBody ChatRequest request) {
        return chatService.ask(request);
    }

    @PostMapping("/message/spring-ai")
    public ChatResponse sendSpringAiMessage(@RequestBody ChatRequest request) {
        return springAiService.ask(request);
    }
}
