package com.example.wiprocodingassessment.viewmodels

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wiprocodingassessment.views.HomeActivity

class SplashScreenViewModel:ViewModel() {
    init {
       launchHomeScreen()
    }

    private val navigateToScreen:MutableLiveData<Class<*>> = MutableLiveData()

    fun getNavigateToScreen():LiveData<Class<*>>{
        return navigateToScreen
    }

    private fun launchHomeScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToScreen.value = HomeActivity::class.java
        },2000L)
    }

}