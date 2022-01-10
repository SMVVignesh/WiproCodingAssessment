package com.example.wiprocodingassessment.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.wiprocodingassessment.R
import com.example.wiprocodingassessment.databinding.ActivityHomeBinding
import com.example.wiprocodingassessment.viewmodels.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private var mViewDataBinding: ActivityHomeBinding? = null
    private var mViewModel: HomeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        addObservers()
    }

    private fun initDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        mViewDataBinding?.lifecycleOwner = this
        mViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private fun addObservers() {

    }
}