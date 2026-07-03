package com.insurance.ai.chatbot.aiinsurnacechatbot.vector;

public class CosineSimilarity {

    public static double calculate(float[] vectorA, float[] vectorB){
        if (vectorA.length != vectorB.length) {
            throw new IllegalArgumentException("Vectors must be of the same length");
        }
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }
        normA = Math.sqrt(normA);
        normB = Math.sqrt(normB);
        if (normA == 0.0 || normB == 0.0) {
            return 0.0; // Avoid division by zero
        }
        return dotProduct / (normA * normB);
    }

}
