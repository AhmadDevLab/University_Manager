package com.login.ric

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.login.ric.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val db = Firebase.firestore
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            val name = binding.etSignUpName.text.toString()
            val email = binding.etSignUpMail.text.toString()
            val password = binding.etSignUpPassword.text.toString()
            val confirmPassword = binding.etSignUpConfirmPassword.text.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this@SignUpActivity, "Fill all the Details", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Repeat Password must be same", Toast.LENGTH_SHORT).show()
            } else {
                // Check if the email already exists
                db.collection("Users").whereEqualTo("userEmail", email).get()
                    .addOnSuccessListener { documents ->
                        if (documents.isEmpty) {
                            // Email does not exist, proceed with registration
                            val user = ModelUser(
                                userName = name,
                                userPassword = password,
                                userEmail = email
                            )
                            db.collection("Users").add(user)
                                .addOnSuccessListener { documentReference ->
                                    user.userId = documentReference.id
                                    db.collection("Users").document(documentReference.id).set(user)
                                        .addOnSuccessListener {

                                            Toast.makeText(this, "Sign Up Successfully", Toast.LENGTH_SHORT).show()
                                            val intent = Intent(this@SignUpActivity, ActivityLogin::class.java)
                                            startActivity(intent)
                                            finish()
                                        }
                                        .addOnFailureListener { e ->
                                            Toast.makeText(
                                                this@SignUpActivity,
                                                "Error is: ${e.localizedMessage}",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(
                                        this@SignUpActivity,
                                        "Error adding document: ${e.localizedMessage}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        } else {
                            // Email already exists, show a toast message
                            Toast.makeText(this, "This email already has an account", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(
                            this@SignUpActivity,
                            "Error checking email: ${e.localizedMessage}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }

        binding.tvSignIn.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, ActivityLogin::class.java))
        }
    }
}

