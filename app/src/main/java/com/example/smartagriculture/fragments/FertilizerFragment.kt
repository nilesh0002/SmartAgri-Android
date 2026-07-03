package com.example.smartagriculture.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.smartagriculture.R
import com.example.smartagriculture.databinding.FragmentFertilizerBinding
import com.example.smartagriculture.repository.FertilizerRepository

class FertilizerFragment : Fragment(R.layout.fragment_fertilizer) {

    private var binding: FragmentFertilizerBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFertilizerBinding.bind(view)

        val cropNames = FertilizerRepository.getAllCropNames()
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            cropNames
        )
        binding?.spinnerCrop?.adapter = adapter

        val soilTypes = listOf("Loamy", "Clay", "Sandy", "Silty", "Peaty", "Saline")
        val soilAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            soilTypes
        )
        binding?.spinnerSoilType?.adapter = soilAdapter

        binding?.btnGetRecommendation?.setOnClickListener {
            val selectedCrop = binding?.spinnerCrop?.selectedItem?.toString() ?: ""
            val soilType = binding?.spinnerSoilType?.selectedItem?.toString() ?: ""

            val recommendation = FertilizerRepository.recommend(selectedCrop, soilType)

            if (recommendation != null) {
                binding?.tvResult?.text =
                    "Recommended: ${recommendation.recommendedFertilizer}\n\nDosage: ${recommendation.dosage}"
            } else {
                binding?.tvResult?.text =
                    "No specific recommendation found for $selectedCrop in $soilType soil.\nTry: NPK balanced fertilizer as a general option."
            }

            binding?.cardResult?.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}