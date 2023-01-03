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
const val BASE_URL = "https://api.yelp.com/v3/"

interface ApiService{
    @GET("businesses/search")
    fun getData(@Query("radius") radius:Int):Call<Model>
}
object ApiFacade{
    var apiServices:ApiService? = null

    @JvmStatic
    fun getApiService():ApiService{
        if (apiServices==null)
            apiServices = create()
        return apiServices as ApiService
    }

fun create():ApiService{
    val interceptor = SCInterceptor()
    val okClient: OkHttpClient.Builder = OkHttpClient.Builder()
    okClient.addInterceptor(interceptor)
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okClient.build())
        .build()
    return retrofit.create(ApiService::class.java)
}

}


class SCInterceptor:Interceptor{

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
}