package com.mecorp.medatcorp.utils

import com.mecorp.medatcorp.services.LoginService

class ApiUtils {
    companion object{
        val baseUrl="http://10.0.2.2:8080/";

        fun getApiLogin() : LoginService{
            return RetrofitClient.getClient(baseUrl).create(LoginService::class.java);
        }
    }
}