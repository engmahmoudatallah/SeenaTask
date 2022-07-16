package com.seenatask.network.apiServices


import com.seenatask.model.MovieResponse
import com.seenatask.network.Urls
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RetroApi {

    @GET(Urls.REVIEWS)
    fun getMovies(
        @Query("query") queryKey: String,
        @Query("api-key") apiKey: String,
    ): Call<MovieResponse>

    @GET(Urls.REVIEWS)
    fun getMoviesRx(
        @Query("query") queryKey: String,
        @Query("api-key") apiKey: String,
    ): Single<MovieResponse>

}