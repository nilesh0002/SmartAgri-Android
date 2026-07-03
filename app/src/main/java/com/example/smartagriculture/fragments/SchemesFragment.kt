package com.example.smartagriculture.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartagriculture.R
import com.example.smartagriculture.adapter.SchemesAdapter
import com.example.smartagriculture.model.Scheme

class SchemesFragment : Fragment(R.layout.fragment_schemes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val rvSchemes = view.findViewById<RecyclerView>(R.id.rvSchemes)
        rvSchemes.layoutManager = LinearLayoutManager(requireContext())
        
        val mockSchemes = listOf(
            Scheme("PM-Kisan Samman Nidhi", "Direct income support of ₹6,000 per year to farmer families.", "Small and marginal farmers"),
            Scheme("Pradhan Mantri Fasal Bima Yojana", "Provides comprehensive crop insurance cover against non-preventable natural risks.", "All farmers growing notified crops"),
            Scheme("Soil Health Card Scheme", "Issues soil health cards to farmers to help them use fertilizers optimally.", "All farmers"),
            Scheme("Kisan Credit Card (KCC)", "Provides timely credit support for cultivation and other needs.", "Farmers, tenant farmers, sharecroppers")
        )
        
        rvSchemes.adapter = SchemesAdapter(mockSchemes)
    }
}