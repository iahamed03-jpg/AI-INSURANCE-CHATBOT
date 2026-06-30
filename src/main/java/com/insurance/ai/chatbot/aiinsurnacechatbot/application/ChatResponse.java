package com.insurance.ai.chatbot.aiinsurnacechatbot.application;

public class ChatResponse {
    private String reply;
    private String model;
    private Long timestamp;

    public ChatResponse() {
    }

    public ChatResponse(String reply, String model, Long timestamp) {
        this.reply = reply;
        this.model = model;
        this.timestamp = timestamp;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
