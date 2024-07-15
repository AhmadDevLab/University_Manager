package com.login.ric

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.login.ric.databinding.ActivityResetBinding

class ResetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResetBinding

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityResetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        var sharedPreferences =MySharedPreferences(this)














        binding.btnConfirm.setOnClickListener()
        {
            val confirmEmail = binding.etResetEmail.text.toString()
            
            if(confirmEmail.isEmpty())
            {
                Toast.makeText(this@ResetActivity, "Fill email", Toast.LENGTH_SHORT).show()
            }
            else{


            }





//            var savedEmail = sharedPreferences.getEmail()
//
//
//            if (confirmEmail.equals(savedEmail))
//            {
//                startActivity(Intent(this@ResetActivity,ResetPasswordActivity::class.java))
//                finish()
//            }
//            else
//            {
//                Toast.makeText(this@ResetActivity, "Email not found", Toast.LENGTH_SHORT).show()
//            }
        }
    }
}