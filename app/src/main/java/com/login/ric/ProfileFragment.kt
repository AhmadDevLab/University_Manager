package com.login.ric

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private val db = Firebase.firestore

    private lateinit var mySharedPreferences: MySharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mySharedPreferences = MySharedPreferences(requireContext())

        val userDocumentId = mySharedPreferences.getUserId()

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val userNameTextView = view.findViewById<TextView>(R.id.tvName)

        db.collection("Users").document(userDocumentId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val userName = document.getString("userName")
                    userNameTextView.text = userName
                } else {
                    userNameTextView.text = "No user found"
                }
            }
            .addOnFailureListener { exception ->
                userNameTextView.text = "Error fetching user data: ${exception.localizedMessage}"
            }

        return view
    }
}
