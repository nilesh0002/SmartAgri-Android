package com.example.smartagriculture.repository

import com.example.smartagriculture.model.Crop

object CropRepository {

    fun getAllCrops(): List<Crop> = listOf(
        Crop(1, "Wheat", "Rabi (Winter)", "Loamy", "Medium",
            "Wheat is a staple cereal crop grown mainly in winter. It needs well-drained loamy soil and moderate irrigation."),
        Crop(2, "Rice", "Kharif (Monsoon)", "Clayey", "High",
            "Rice requires standing water for most of its growth period and thrives in clayey, water-retentive soil."),
        Crop(3, "Maize", "Kharif/Rabi", "Sandy Loam", "Medium",
            "Maize is a versatile crop grown in both seasons, preferring well-drained sandy loam soil."),
        Crop(4, "Sugarcane", "Year-round", "Loamy", "High",
            "Sugarcane is a long-duration crop needing consistent water supply and fertile loamy soil."),
        Crop(5, "Cotton", "Kharif (Monsoon)", "Black Soil", "Medium",
            "Cotton grows best in black cotton soil and requires a warm climate with moderate rainfall."),
        Crop(6, "Mustard", "Rabi (Winter)", "Loamy", "Low",
            "Mustard is a low-water-requirement oilseed crop, ideal for winter cultivation in loamy soil.")
    )

    fun getCropById(id: Int): Crop? = getAllCrops().find { it.id == id }
}