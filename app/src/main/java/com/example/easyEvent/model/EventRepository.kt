package com.example.easyEvent.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.easyEvent.network.ApiClient
import com.example.easyEvent.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventRepository {

    private var apiInterface: ApiInterface? = null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun fetchAllEvents(): LiveData<List<EventModel>> {
        val data = MutableLiveData<List<EventModel>>()

        apiInterface?.fetchAllEvents()?.enqueue(object: Callback<List<EventModel>> {

            override fun onFailure(call: Call<List<EventModel>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<List<EventModel>>,
                response: Response<List<EventModel>>
            ) {

                val res = response.body()
                if(response.code() == 200 && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }

            }

        })

        return data
    }
}