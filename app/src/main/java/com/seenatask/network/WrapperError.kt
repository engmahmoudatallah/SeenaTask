package com.seenatask.network


class WrapperError(errorsCode: Int, statuCode: String?, message: String?) : RuntimeException() {
    private var messages: String? = null
    private var statusCode: String? = null
    var errorCode = 0

    init {
        messages = message
        statusCode = statuCode
        errorCode = errorsCode
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val apiError = other as WrapperError
        if (errorCode != apiError.errorCode) return false
        return if (statusCode != apiError.statusCode) false else message == apiError.message
    }

    override fun hashCode(): Int {
        var result = errorCode
        result = 31 * result + if (statusCode != null) statusCode.hashCode() else 0
        result = 31 * result + (message?.hashCode() ?: 0)
        return result
    }

}