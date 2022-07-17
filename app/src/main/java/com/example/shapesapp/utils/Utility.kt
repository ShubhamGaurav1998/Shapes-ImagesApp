package com.example.shapesapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

object Utility {


    fun Context.isInternetAvailable(): Boolean {
        try {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return if (netInfo != null && netInfo.isConnected) {
                true
            } else {
                Toast.makeText(applicationContext,"Internet not available. Please try again!!", Toast.LENGTH_SHORT).show()
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

}