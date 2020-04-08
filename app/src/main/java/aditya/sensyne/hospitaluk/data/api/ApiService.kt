package aditya.sensyne.hospitaluk.data.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming

interface ApiService {
    @Streaming
    @GET ("data/foi/Hospital.csv/")
    fun getHospitals(): Call<ResponseBody>


}