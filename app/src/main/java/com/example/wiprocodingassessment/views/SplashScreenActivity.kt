package com.example.wiprocodingassessment.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.wiprocodingassessment.R
import com.example.wiprocodingassessment.databinding.ActivitySplashScreenBinding
import com.example.wiprocodingassessment.viewmodels.SplashScreenViewModel

class SplashScreenActivity : AppCompatActivity() {
    private var mViewDataBinding: ActivitySplashScreenBinding? = null
    private var mViewModel: SplashScreenViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        addObservers()
    }

    /*
        To Bind the data
     */
    private fun initDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        mViewDataBinding?.lifecycleOwner = this
        mViewModel = ViewModelProvider(this)[SplashScreenViewModel::class.java]
    }

    /*
        Add observers in this method
     */
    private fun addObservers() {
        mViewModel?.getNavigateToScreen()?.observe(this, { splashScreen ->
            startActivity(Intent(this, splashScreen))
            finishAffinity()
        })
    }
}