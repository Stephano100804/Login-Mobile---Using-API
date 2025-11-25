package com.mecorp.medatcorp.services


import com.mecorp.medatcorp.entidad.ad_tmusua
import retrofit2.Call
import retrofit2.http.*;

interface LoginService {

    @GET("/usuario/login")
    fun login(
        @Query("co_usua") usuario: String,
        @Query("no_clav") clave: String
    ): Call<ad_tmusua>

}