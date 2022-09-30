package com.jine.espressotalk.data.utils

import retrofit2.Response
import java.net.UnknownHostException

object RequestParser {
    fun <T> parse(request: () -> Response<T>): T {
        try {
            val response = request()
            return response.body()!!
        } catch (e: Exception) {
            throw when (e) {
                is UnknownHostException -> Exception("No signal found... please check your internet connection")
                else -> Exception("Oops.. something wrong happened\n${e.message}")
            }
        }
    }
}