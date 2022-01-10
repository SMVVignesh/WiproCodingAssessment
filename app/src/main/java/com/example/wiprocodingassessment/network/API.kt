package com.example.wiprocodingassessment.network


import com.example.wiprocodingassessment.models.FactsModel
import retrofit2.Call
import retrofit2.http.*


interface API {

    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getFacts(): Call<FactsModel>
}