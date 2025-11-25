package com.mecorp.medatcorp.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    //AGREGAR CONEXIÓN PARA LOS PERMISOS
    companion object {
        fun getClient(URL:String):Retrofit{
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit
        }
    }
}