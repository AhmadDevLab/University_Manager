package com.login.ric

import android.content.Context

class MySharedPreferences(context: Context){

    var sharedPreferences = context.getSharedPreferences("com.login.ric",Context.MODE_PRIVATE)
    var editor = sharedPreferences.edit()

    fun saveEmail(email:String)
    {
        editor.putString("Email",email)
        editor.apply()
    }

    fun savePassword(password:String)
    {
        editor.putString("Password",password)
        editor.apply()
    }
    fun saveName(name:String)
    {
        editor.putString("FirstName",name)
        editor.apply()
    }

    fun saveUserId(userId:String){
        editor.putString("SavedUserId",userId)
        editor.apply()
    }
    fun isLogedIn(isLogin : Boolean)
    {
        editor.putBoolean("isLogin",isLogin)
        editor.apply()
    }

    fun getUserId():String
    {
        return sharedPreferences.getString("SavedUserId","Default Id") !!
    }

    fun getName():String
    {
        return sharedPreferences.getString("FirstName","Default Email") !!
    }


    fun getEmail():String
    {
       return sharedPreferences.getString("Email","Default Email") !!
    }
    fun getPassword():String
    {
       return sharedPreferences.getString("Password","Default Password") !!
    }

    fun getIsLogIN():Boolean
    {
        return sharedPreferences.getBoolean("isLogin",false)
    }

}