package com.example.wiprocodingassessment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wiprocodingassessment.models.FactsModel
import com.example.wiprocodingassessment.network.BaseRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private var loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var messageLiveData: MutableLiveData<String?> = MutableLiveData()
    private var userData: MutableLiveData<FactsModel> = MutableLiveData()

    init {
        getFacts()
    }

    fun loadingObservable(): LiveData<Boolean> {
        return loadingLiveData
    }

    fun messageObservable(): LiveData<Boolean> {
        return loadingLiveData
    }

    fun factsObservable(): LiveData<FactsModel> {
        return userData
    }

    fun getFacts() {
        loadingLiveData.value = true
        BaseRetrofit.getApi().getFacts().enqueue(object : Callback<FactsModel> {
            override fun onResponse(call: Call<FactsModel>, response: Response<FactsModel>) {
                loadingLiveData.value = false
                userData.value = response.body()
            }

            override fun onFailure(call: Call<FactsModel>, t: Throwable) {
                loadingLiveData.value = false
                messageLiveData.value = t.message
            }

        })
    }
}