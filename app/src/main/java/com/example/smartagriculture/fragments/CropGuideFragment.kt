package com.example.smartagriculture.fragments

import android.os.Bundle
import android.os.Bundle as AndroidBundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartagriculture.R
import com.example.smartagriculture.adapter.CropAdapter
import com.example.smartagriculture.databinding.FragmentCropGuideBinding
import com.example.smartagriculture.repository.CropRepository

class CropGuideFragment : Fragment(R.layout.fragment_crop_guide) {

    private var binding: FragmentCropGuideBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCropGuideBinding.bind(view)

        val crops = CropRepository.getAllCrops()

        binding?.rvCrops?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvCrops?.adapter = CropAdapter(crops) { crop ->
            val bundle = AndroidBundle().apply {
                putInt("cropId", crop.id)
            }
            findNavController().navigate(R.id.action_cropGuideFragment_to_cropDetailFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}