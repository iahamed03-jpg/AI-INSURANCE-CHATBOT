package com.insurance.ai.chatbot.aiinsurnacechatbot.vector;

public class VectorDocument {
    private String id;
    private String text;
    private float[] embedding;

    public VectorDocument(String id, String text, float[] embedding) {
        this.id = id;
        this.text = text;
        this.embedding = embedding;
    }

    public String getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public float[] getEmbedding() {
        return embedding;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setEmbedding(float[] embedding) {
        this.embedding = embedding;
    }

}
