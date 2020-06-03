package com.hurtarte.alexguitz

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.hurtarte.alexguitz.model.ErrorResponse

import okhttp3.ResponseBody
import java.io.IOException

fun Context.showErrorMessage(errorBody: ResponseBody, duration: Int = Toast.LENGTH_SHORT) {

    val gson = GsonBuilder().create()
    try {
        val errorResponse = gson.fromJson(errorBody.string(), ErrorResponse::class.java)
        toast(errorResponse.message!!, duration)
    } catch (e: IOException) {
        Log.e("Exception ", e.toString())
    }
}

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}