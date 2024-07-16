package com.login.ric

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.login.ric.databinding.ActivityResetBinding


class ResetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResetBinding
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityResetBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnConfirm.setOnClickListener()
        {
            val confirmEmail = binding.etResetEmail.text.toString()
            
            if(confirmEmail.isEmpty())
            {
                Toast.makeText(this@ResetActivity, "Fill email", Toast.LENGTH_SHORT).show()
            }
            else{
                db.collection("Users")
                    .whereEqualTo("userEmail", confirmEmail)
                    .limit(1) // Ensure only one document is retrieved
                    .get()
                    .addOnSuccessListener { documents ->
                        if (documents.isEmpty) {
                            Toast.makeText(this, "No user found with this email", Toast.LENGTH_SHORT).show()
                            return@addOnSuccessListener
                        }
                        else {
                            Toast.makeText(this, "User found", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@ResetActivity, ResetPasswordActivity:: class.java))
                            finish()
                        }
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(this, "Error: ${exception.localizedMessage}", Toast.LENGTH_SHORT).show()
                    }


            }

        }
    }
}