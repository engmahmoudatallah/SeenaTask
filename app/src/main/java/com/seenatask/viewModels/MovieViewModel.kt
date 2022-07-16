package com.seenatask.viewModels

import android.annotation.SuppressLint
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.widget.ContentLoadingProgressBar
import com.seenatask.base.BaseViewModel
import com.seenatask.model.MovieResponse
import com.seenatask.model.ResultsItem
import com.seenatask.network.RetrofitClientServices
import com.seenatask.network.apiServices.RetroApi
import retrofit2.Call
import retrofit2.Response


class MovieViewModel : BaseViewModel<MovieResponse, List<ResultsItem?>?>() {


    var modelCall: Call<MovieResponse>? = null


    @SuppressLint("StaticFieldLeak")
    var mLoadingProgressBar: ContentLoadingProgressBar? = null

    @SuppressLint("StaticFieldLeak")
    var mLinearProgress: LinearLayoutCompat? = null
    fun getData(
        loadingProgressBar: ContentLoadingProgressBar,
        linearProgress: LinearLayoutCompat,
        key: String,
        apiKey: String,
    ) {
        mLoadingProgressBar = loadingProgressBar
        mLinearProgress = linearProgress
        val retroApi: RetroApi = RetrofitClientServices.instance
        modelCall = retroApi.getMovies(key, apiKey)

        mLoadingProgressBar?.show()
        callApi()

    }

    override val call: Call<MovieResponse>
        get() = modelCall!!

    override fun onSuccess(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
        if (response?.isSuccessful!!) {
            setLiveData(response.body()?.results)
            mLoadingProgressBar?.hide()
            mLinearProgress?.visibility = View.GONE

        } else if (response.code() == 404) {
            mLoadingProgressBar?.hide()
            mLinearProgress?.visibility = View.GONE
        }
    }

    override fun onError(call: Call<MovieResponse>?, t: Throwable?) {
        setLiveData(null)
        handleApiError(t!!)
        mLoadingProgressBar?.hide()
        mLinearProgress?.visibility = View.GONE
    }

}



