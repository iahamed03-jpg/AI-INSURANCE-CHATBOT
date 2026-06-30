package com.insurance.ai.chatbot.aiinsurnacechatbot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import com.insurance.ai.chatbot.aiinsurnacechatbot.application.ChatRequest;
import com.insurance.ai.chatbot.aiinsurnacechatbot.application.ChatResponse;
import com.insurance.ai.chatbot.aiinsurnacechatbot.service.ChatService;

class ChatControllerTest {

    @Test
    void shouldReturnReplyForChatRequest() {
        ChatService chatService = Mockito.mock(ChatService.class);
        when(chatService.ask(org.mockito.ArgumentMatchers.any(ChatRequest.class)))
                .thenReturn(new ChatResponse("Here is your insurance answer", "llama3.2", 1L));

        ChatController controller = new ChatController(chatService);

        ChatResponse response = controller.sendMessage(new ChatRequest("What is covered in my policy?"));

        assertEquals("Here is your insurance answer", response.getReply());
    }
}
