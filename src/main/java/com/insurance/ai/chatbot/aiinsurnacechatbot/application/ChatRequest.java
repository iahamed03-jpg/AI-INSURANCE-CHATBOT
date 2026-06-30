package com.insurance.ai.chatbot.aiinsurnacechatbot.application;

public class ChatRequest {
    private String text;
    private String message;

    public ChatRequest() {
    }

    public ChatRequest(String text) {
        this.text = text;
        this.message = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMessage() {
        return message != null ? message : text;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
