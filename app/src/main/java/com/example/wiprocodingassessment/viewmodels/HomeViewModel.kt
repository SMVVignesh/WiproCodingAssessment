package com.example.wiprocodingassessment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wiprocodingassessment.models.FactsModel
import com.example.wiprocodingassessment.network.BaseRetrofit
import com.example.wiprocodingassessment.utils.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private var loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var pullToRefreshLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var messageLiveData: SingleLiveEvent<String?> = SingleLiveEvent()
    private var userData: MutableLiveData<FactsModel> = MutableLiveData()

    init {
        getFacts()
    }

    /*
          @return Loading Observable
     */
    fun loadingObservable(): LiveData<Boolean> {
        return loadingLiveData
    }

    /*
          @return pull to refresh Observable
     */
    fun pullToRefreshObservable(): LiveData<Boolean> {
        return pullToRefreshLiveData
    }

    /*
        @return message Observable
     */
    fun messageObservable(): LiveData<String?> {
        return messageLiveData
    }

    /*
        @return facts Observable
     */
    fun factsObservable(): LiveData<FactsModel> {
        return userData
    }

    /*
      To Call Fact Api
     */
    fun getFacts(pullToRefresh: Boolean = false) {
        if (pullToRefresh) {
            pullToRefreshLiveData.value = true
        } else {
            loadingLiveData.value = true
        }
        BaseRetrofit.getApi().getFacts().enqueue(object : Callback<FactsModel> {
            override fun onResponse(call: Call<FactsModel>, response: Response<FactsModel>) {
                loadingLiveData.value = false
                pullToRefreshLiveData.value = false
                if (response.isSuccessful) {
                    userData.value = response.body()
                } else {
                    messageLiveData.value = response.message()
                }
            }

            override fun onFailure(call: Call<FactsModel>, t: Throwable) {
                loadingLiveData.value = false
                messageLiveData.value = t.message
            }

        })
    }
}