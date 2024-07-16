package com.login.ric

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.login.ric.databinding.ActivityResetPasswordBinding

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private val db = Firebase.firestore
    private lateinit var mySharedPreferences: MySharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mySharedPreferences = MySharedPreferences(this)

        val userDocumentId = mySharedPreferences.getUserId()

        binding.btnReset.setOnClickListener()
        {

            var newPassword = binding.etNewPassword.text.toString()
            var confirmPassword = binding.etConfirmPassword.text.toString()

            if (newPassword.equals(confirmPassword))
            {
                db.collection("Users").document(userDocumentId)
                    .update("userPassword", newPassword)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@ResetPasswordActivity,ActivityLogin:: class.java))
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error updating password: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                    }
            }
            else
            {
                Toast.makeText(this@ResetPasswordActivity, "passwords don't match", Toast.LENGTH_SHORT).show()
            }
        }


    }
}