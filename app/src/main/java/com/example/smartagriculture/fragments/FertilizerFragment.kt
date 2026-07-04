package com.example.smartagriculture.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.smartagriculture.R
import com.example.smartagriculture.databinding.FragmentFertilizerBinding
import com.example.smartagriculture.repository.FertilizerRepository
import com.example.smartagriculture.network.GeminiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FertilizerFragment : Fragment(R.layout.fragment_fertilizer) {

    private var binding: FragmentFertilizerBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFertilizerBinding.bind(view)

        val cropNames = listOf(
            "Wheat", "Rice", "Maize", "Sugarcane", "Cotton", "Mustard", "Soybean", "Groundnut", 
            "Bajra (Pearl Millet)", "Jowar (Sorghum)", "Barley", "Oats", "Ragi", 
            "Tomato", "Potato", "Onion", "Garlic", "Ginger", "Turmeric",
            "Cabbage", "Cauliflower", "Brinjal (Eggplant)", "Okra (Ladyfinger)", "Spinach",
            "Apple", "Mango", "Banana", "Orange", "Papaya", "Grapes", "Pomegranate",
            "Coffee", "Tea", "Rubber", "Coconut", "Cashew", "Almond",
            "Chana (Chickpea)", "Toor (Pigeon Pea)", "Moong (Green Gram)", "Urad (Black Gram)"
        )
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            cropNames
        )
        binding?.spinnerCrop?.setAdapter(adapter)

        val soilTypes = listOf(
            "Alluvial Soil", "Black Soil (Regur)", "Red Soil", "Laterite Soil", 
            "Desert/Arid Soil", "Mountain/Forest Soil", "Peat/Marshy Soil",
            "Loamy", "Clayey", "Sandy", "Silty", "Chalky", "Saline", "Alkaline"
        )
        val soilAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            soilTypes
        )
        binding?.spinnerSoilType?.setAdapter(soilAdapter)

        binding?.btnGetRecommendation?.setOnClickListener {
            val selectedCrop = binding?.spinnerCrop?.text?.toString() ?: ""
            val soilType = binding?.spinnerSoilType?.text?.toString() ?: ""

            if (selectedCrop.isEmpty() || soilType.isEmpty()) {
                binding?.tvResult?.text = "Please select or type both crop and soil type."
                binding?.cardResult?.visibility = View.VISIBLE
                return@setOnClickListener
            }

            binding?.btnGetRecommendation?.isEnabled = false
            binding?.btnGetRecommendation?.text = "Getting AI Recommendation..."
            binding?.cardResult?.visibility = View.GONE

            CoroutineScope(Dispatchers.IO).launch {
                val recommendation = GeminiService.getFertilizerRecommendation(selectedCrop, soilType)
                
                withContext(Dispatchers.Main) {
                    binding?.tvResult?.text = recommendation
                    binding?.cardResult?.visibility = View.VISIBLE
                    binding?.btnGetRecommendation?.isEnabled = true
                    binding?.btnGetRecommendation?.text = "Get Recommendation"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}