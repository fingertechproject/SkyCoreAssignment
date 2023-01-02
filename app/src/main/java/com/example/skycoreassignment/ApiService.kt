package com.example.skycoreassignment

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY = "XPFgzKwZGK1yqRxHi0d5xsARFOLpXIvccQj5jekqTnysweGyoIfVUHcH2tPfGq5Oc9kwKHPkcOjk2d1Xobn7aTjOFeop8x41IUfVvg2Y27KiINjYPADcE7Qza0RkX3Yx"
const val BASE_URL = "https://api.yelp.com/v3/businesses/search"

interface ApiServiceInterface{
    @GET()
    fun getData(@Query("radius") radius:Int):Call<Model>

}
object ApiService {
    // val instance : ApiServiceInterface
    init {
        val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpClient.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original: Request = chain.request()
                val request = original.newBuilder()
                    .url("https://api.yelp.com/v3/businesses/search?location=NYC&radius=500&sort_by=best_match&limit=20")
                    .get()
                    .addHeader("accept", "application/json")
                    .header("Authorization", API_KEY)
                    .method(original.method(), original.body())
                    .build()
                return chain.proceed(request)
            }
        })
        /* val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiServiceInterface::class.java)
    }*/
    }
}