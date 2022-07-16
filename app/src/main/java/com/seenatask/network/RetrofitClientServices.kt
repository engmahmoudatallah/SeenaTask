package com.seenatask.network


import com.seenatask.app.Constant
import com.seenatask.network.apiServices.RetroApi
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

object RetrofitClientServices {
    /********
     * this class is used to initialize global retrofit instance
     */
    /**
     * Get Retrofit Instance
     */

    private fun getRetrofitInstance(): Retrofit {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)


        val httpClient = OkHttpClient.Builder()
            .callTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl(Urls.BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }


    private class HeaderInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            //   long maxStale = 60 * 60 * 24 * 30; // Offline cache available for 30 days , max-stale=" + maxStale
            val request: Request = if (Constant.API_TOKEN.isNotEmpty()) chain.request()
                .newBuilder()
                .addHeader("Accept", "application/json")
                // .addHeader("Authorization", "Bearer " + Constant.API_TOKEN)
                .build() else chain.request()
                .newBuilder() //   .header("Cache-Control", "public, max-stale=" + maxStale)
                .addHeader("Accept", "application/json")
                .build()
            return chain.proceed(request)
        }
    }

    @Singleton
    val instance: RetroApi by lazy {
        getRetrofitInstance().create(RetroApi::class.java)
    }
}


