package com.login.ric

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.login.ric.databinding.ActivityLoginBinding

class ActivityLogin : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val db = Firebase.firestore
    private lateinit var mySharedPreferences: MySharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mySharedPreferences = MySharedPreferences(this@ActivityLogin)

        binding.btnLogin.setOnClickListener {
            val loginEmail = binding.etEmail.text.toString().trim()
            val loginPassword = binding.etPassword.text.toString().trim()

            if (loginEmail.isEmpty() || loginPassword.isEmpty()) {
                Toast.makeText(this, "Enter Email & Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            db.collection("Users")
                .whereEqualTo("userEmail", loginEmail)
                .limit(1) // Ensure only one document is retrieved
                .get()
                .addOnSuccessListener { documents ->
                    if (documents.isEmpty) {
                        Toast.makeText(this, "No user found with this email", Toast.LENGTH_SHORT).show()
                        return@addOnSuccessListener
                    }

                    val document = documents.documents[0]
                    val storedPassword = document.getString("userPassword")?.trim()
                    if (storedPassword == loginPassword) {
                       mySharedPreferences.isLogedIn(isLogin = true)

                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Error: ${exception.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
        }

        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.tvForget.setOnClickListener {
            startActivity(Intent(this, ResetActivity::class.java))
            finish()
        }
    }
}
