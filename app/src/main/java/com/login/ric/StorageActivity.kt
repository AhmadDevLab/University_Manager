package com.login.ric

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.login.ric.databinding.ActivityStorageBinding

class StorageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStorageBinding
    private lateinit var shared : MySharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        shared = MySharedPreferences(this@StorageActivity)

        binding.tvEmail.text = shared.getEmail()
        binding.tvPassword.text = shared.getPassword()

    }
}