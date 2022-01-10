package com.example.wiprocodingassessment.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wiprocodingassessment.R
import com.example.wiprocodingassessment.adapter.FactsAdapter
import com.example.wiprocodingassessment.databinding.ActivityHomeBinding
import com.example.wiprocodingassessment.viewmodels.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private var mViewDataBinding: ActivityHomeBinding? = null
    private var mViewModel: HomeViewModel? = null
    private var userAdapter: FactsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        setUpRecyclerView()
        addObservers()
    }

    private fun callFactsApi() {
        mViewModel?.getFacts()
    }

    private fun setUpRecyclerView() {
        userAdapter = FactsAdapter()
        mViewDataBinding?.rVFacts?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }
    }

    private fun initDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        mViewDataBinding?.lifecycleOwner = this
        mViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private fun addObservers() {
        mViewModel?.loadingObservable()?.observe(this, { showLoading ->
            mViewDataBinding?.pBFacts?.visibility = if (showLoading) View.VISIBLE else View.GONE
        })
        mViewModel?.messageObservable()?.observe(this, {


        })
        mViewModel?.factsObservable()?.observe(this, {
            mViewDataBinding?.txtTitle?.text = it.title
            userAdapter?.updateList(it.rows ?: ArrayList())
        })
    }
}