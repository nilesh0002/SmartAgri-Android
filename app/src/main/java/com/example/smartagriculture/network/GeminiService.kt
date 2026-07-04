package com.example.smartagriculture.network

import com.google.ai.client.generativeai.GenerativeModel

object GeminiService {
    // Note: The user should provide an API key in a secure way in production.
    // For this prototype, we will allow it to be passed in or fail gracefully.
    private const val API_KEY = "YOUR_API_KEY_HERE" 
    
    // We try to initialize the model. If it fails due to missing key, we handle it in getRecommendation.
    private val generativeModel = try {
        GenerativeModel(
            modelName = "gemini-flash-latest",
            apiKey = API_KEY
        )
    } catch (e: Exception) {
        null
    }

    suspend fun getFertilizerRecommendation(crop: String, soil: String): String {
        if (API_KEY == "YOUR_API_KEY_HERE" || generativeModel == null) {
            return "AI Recommendation Unavailable: Please provide a valid Gemini API key in GeminiService.kt.\n\nFallback Recommendation:\nNitrogen-rich fertilizers usually help most crops in $soil soil."
        }
        
        val prompt = """
            You are an expert agronomist. 
            A farmer is asking for a detailed fertilizer recommendation for growing $crop in $soil soil.
            Provide a comprehensive, professional recommendation including:
            1. An analysis of how $crop performs in $soil soil.
            2. The best fertilizer type (e.g., specific NPK ratio, organic vs synthetic, micro-nutrients needed).
            3. Recommended dosage per acre and application schedule.
            4. A pro tip for maximizing yield with this specific crop and soil combination.
            Make it clear, easy to read, and highly specific to $crop and $soil. Do not use markdown formatting like asterisks.
        """.trimIndent()

        return try {
            val response = generativeModel.generateContent(prompt)
            response.text ?: "Could not generate a recommendation at this time."
        } catch (e: Exception) {
            "Error communicating with AI service: ${e.localizedMessage}"
        }
    }
}
