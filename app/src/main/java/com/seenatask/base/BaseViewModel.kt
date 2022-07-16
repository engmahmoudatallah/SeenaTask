package com.seenatask.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonSyntaxException
import com.seenatask.network.WrapperError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection

abstract class BaseViewModel<Model, DataItem> : ViewModel() {
    var errorMessage: String? = null
    var errorCode: Throwable? = null

    companion object {
        private const val TAG = "BaseViewModel"
        const val API_STATUS_CODE_LOCAL_ERROR = 0
    }


    private val liveData = MutableLiveData<DataItem>()


    protected abstract val call: Call<Model>
    protected abstract fun onSuccess(call: Call<Model>?, response: Response<Model>?)
    protected abstract fun onError(call: Call<Model>?, t: Throwable?)


    protected open fun callApi() {
        call.enqueue(object : Callback<Model> {
            override fun onResponse(call: Call<Model>, response: Response<Model>) {
                onSuccess(call, response)
            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                handleApiError(t)
                onError(call, t)
            }
        })
    }

    open fun getLiveData(): MutableLiveData<DataItem>? {
        return liveData
    }

    protected open fun setLiveData(list: DataItem?) {
        liveData.postValue(list!!)
    }

    private fun logErrorMessage(message: String?, throwable: Throwable?) {
        Log.e(TAG, "logErrorMessage: $message", throwable)
        errorMessage = message
        errorCode = throwable
    }


    fun handleApiError(error: Throwable) {
        if (error is HttpException) {
            when (error.code()) {
                HttpsURLConnection.HTTP_UNAUTHORIZED -> logErrorMessage("HTTP_UNAUTHORIZED", error)
                HttpsURLConnection.HTTP_FORBIDDEN -> logErrorMessage("HTTP_FORBIDDEN", error)
                HttpsURLConnection.HTTP_INTERNAL_ERROR -> logErrorMessage("HTTP_INTERNAL_ERROR",
                    error)
                HttpsURLConnection.HTTP_BAD_REQUEST -> logErrorMessage("HTTP_BAD_REQUEST", error)
                HttpsURLConnection.HTTP_UNSUPPORTED_TYPE -> logErrorMessage("HTTP_UNSUPPORTED_TYPE",
                    error)
                HttpsURLConnection.HTTP_PAYMENT_REQUIRED -> logErrorMessage("HTTP_PAYMENT_REQUIRED",
                    error)
                API_STATUS_CODE_LOCAL_ERROR -> logErrorMessage("No Internet", error)
                else -> logErrorMessage(error.getLocalizedMessage(), error)
            }
        } else if (error is WrapperError) {
            logErrorMessage(error.message, error)
        } else if (error is JsonSyntaxException) {
            logErrorMessage("Known error", error)
        }
    }
}