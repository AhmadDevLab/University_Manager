package com.login.ric

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.login.ric.databinding.ActivityResetPasswordBinding

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var sharedPreferences =MySharedPreferences(this)



        binding.btnReset.setOnClickListener()
        {

            var newPassowrd = binding.etNewPassword.text.toString()
            var confirmPassword = binding.etConfirmPassword.text.toString()

            if (newPassowrd.equals(confirmPassword))
            {
                sharedPreferences.savePassword(binding.etConfirmPassword.text.toString())
                startActivity(Intent(this@ResetPasswordActivity,ActivityLogin::class.java))
                finish()
            }
            else
            {
                Toast.makeText(this@ResetPasswordActivity, "passwords don't match", Toast.LENGTH_SHORT).show()
            }
        }


    }
}