package com.login.ric

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.login.ric.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    private lateinit var sharedPreferences: MySharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ReplaceFragment(HomeFragment())
        binding.bottomNav.setOnItemSelectedListener {menu->


            if(menu.itemId==R .id.home)
            {
                ReplaceFragment(HomeFragment())
            }
            else if(menu.itemId==R.id.profile)
            {
                ReplaceFragment(ProfileFragment())
            }
            else if(menu.itemId==R.id.schedule)
            {
                ReplaceFragment(ScheduleFragment())
            }
            true


        }



    }

    private fun ReplaceFragment(fragment: Fragment){

        var fragmentManager = supportFragmentManager
        var transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,fragment)
        transaction.commit()

    }




}
