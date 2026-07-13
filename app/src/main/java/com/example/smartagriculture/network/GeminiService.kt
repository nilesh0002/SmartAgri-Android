package com.example.smartagriculture.network

import com.example.smartagriculture.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel

object GeminiService {
    private val API_KEY = BuildConfig.GEMINI_API_KEY
    
    // We try to initialize the model. If it fails due to missing key, we handle it in getRecommendation.
    private val generativeModel = try {
        if (API_KEY.isNotBlank() && API_KEY != "YOUR_API_KEY_HERE") {
            GenerativeModel(
                modelName = "gemini-flash-latest",
                apiKey = API_KEY
            )
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }

    suspend fun getFertilizerRecommendation(crop: String, soil: String): String {
        if (generativeModel == null) {
            // Mock response for UI testing
            kotlinx.coroutines.delay(1500) // Simulate network delay
            return """
                Fertilizer Recommendation for $crop in $soil:
                
                1. Analysis: $crop grows moderately well in $soil. This soil type tends to require more frequent nutrient replenishment.
                2. Fertilizer Type: Use a balanced NPK fertilizer (like 10-10-10) enriched with Zinc and Boron. Organic compost is highly recommended.
                3. Dosage: Apply 50 kg per acre in two split doses (baseline and 30 days after planting).
                4. Pro Tip: Maintain adequate soil moisture when applying fertilizer to ensure optimal nutrient absorption!
                
                (Note: This is a simulated response. Please add a valid Google Gemini API key to local.properties for real AI recommendations.)
            """.trimIndent()
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
            "AI Recommendation Unavailable: Please provide a valid Gemini API key in local.properties.\n\nFallback Recommendation:\nNitrogen-rich fertilizers usually help most crops in $soil soil."
        }
    }
}
