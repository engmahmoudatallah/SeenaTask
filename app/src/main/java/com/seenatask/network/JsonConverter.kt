package com.seenatask.network

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException

object JsonConverter {
    fun getMessage(response: Response<*>): String {
        try {
            var jsonObject: JSONObject? = null
            if (response.errorBody() != null) {
                jsonObject = JSONObject(response.errorBody()!!.string())
            }
            if (jsonObject != null) {
                return jsonObject.getString("message")
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return ""
    }

    fun getError(response: Response<*>): String {
        try {
            var jsonObject: JSONObject? = null
            if (response.errorBody() != null) {
                jsonObject = JSONObject(response.errorBody()!!.string())
            }
            if (jsonObject != null) {
                return jsonObject.getString("error")
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return ""
    }
}