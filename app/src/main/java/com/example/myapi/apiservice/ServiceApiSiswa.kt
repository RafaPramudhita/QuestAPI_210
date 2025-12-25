package com.example.myapi.apiservice

import com.example.myapi.modeldata.DataSiswa
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceApiSiswa {

    @GET("bacateman.php")
    suspend fun getSiswa(): List<DataSiswa>


}
