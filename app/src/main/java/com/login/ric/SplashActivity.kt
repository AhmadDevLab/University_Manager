package com.login.ric

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.login.ric.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var mySharedPreferences: MySharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mySharedPreferences = MySharedPreferences(this)


        Handler(Looper.getMainLooper()).postDelayed({
            if(mySharedPreferences.getIsLogIN() == false)
            {
                startActivity(Intent(this@SplashActivity,ActivityLogin::class.java))
                finish()
            }
           else{
               startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                finish()
            }
        }, 2000)

    }
}