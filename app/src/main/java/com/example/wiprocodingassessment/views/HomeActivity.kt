package com.example.wiprocodingassessment.views

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wiprocodingassessment.R
import com.example.wiprocodingassessment.adapter.FactsAdapter
import com.example.wiprocodingassessment.databinding.ActivityHomeBinding
import com.example.wiprocodingassessment.viewmodels.HomeViewModel
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {
    private var mViewDataBinding: ActivityHomeBinding? = null
    private var mViewModel: HomeViewModel? = null
    private var userAdapter: FactsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        setUpSwipeRefresh()
        setUpRecyclerView()
        addObservers()
    }

    /*
        Adding Refresh Listener
     */
    private fun setUpSwipeRefresh() {
        mViewDataBinding?.sRLFacts?.setOnRefreshListener {
            callFactsApi()
        }
    }

    /*
        Call Facts Api
     */
    private fun callFactsApi() {
        Log.e("API_CALLED", "FACTS CALLED")
        mViewModel?.getFacts(true)
    }

    /*
        Adding Facts RecyclerView and Adapter
     */
    private fun setUpRecyclerView() {
        userAdapter = FactsAdapter()
        mViewDataBinding?.rVFacts?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }
    }

    /*
        Initialize Data Binding
     */
    private fun initDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        mViewDataBinding?.lifecycleOwner = this
        mViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    /*
        Adding LiveData Observers
     */
    private fun addObservers() {
        mViewModel?.loadingObservable()?.observe(this, { showLoading ->
            mViewDataBinding?.pBFacts?.visibility = if (showLoading) View.VISIBLE else View.GONE
        })
        mViewModel?.pullToRefreshObservable()?.observe(this, { showLoading ->
            mViewDataBinding?.sRLFacts?.isRefreshing = showLoading
        })
        mViewModel?.messageObservable()?.observe(this, { message ->
            showErrorMessage(message)
        })
        mViewModel?.factsObservable()?.observe(this, {
            mViewDataBinding?.txtTitle?.text = it.title
            userAdapter?.updateList(it.rows ?: ArrayList())
        })
    }

    /*
        Show the Error Message
     */
    private fun showErrorMessage(message: String?) {
        Snackbar.make(findViewById(android.R.id.content), message ?: "", Snackbar.LENGTH_LONG)
            .show()
    }
}