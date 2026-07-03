package com.example.smartagriculture.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartagriculture.R

class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.postDelayed({
            val prefs = requireContext().getSharedPreferences("smart_agri_prefs", 0)
            val selectedLanguage = prefs.getString("selected_language", null)

            if (selectedLanguage != null) {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_languageFragment)
            }
        }, 2000)
    }
}