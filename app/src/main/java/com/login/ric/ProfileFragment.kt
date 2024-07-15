package com.login.ric

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Initialize TextViews
//        val tvFirst: TextView = view.findViewById(R.id.tvFirst)
//        val tvLast: TextView = view.findViewById(R.id.tvLast)
//
//        // Get data from SharedPreferences
//        val sharedPreferences = MySharedPreferences(requireContext())
//        val firstName = sharedPreferences.getFirstName()
//        val lastName = sharedPreferences.getLastName()
//
//        // Set the text of TextViews
//        tvFirst.text = firstName
//        tvLast.text = lastName

        return view
    }
}
