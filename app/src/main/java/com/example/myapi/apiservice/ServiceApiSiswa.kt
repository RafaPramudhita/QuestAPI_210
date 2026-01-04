package com.example.myapi.apiservice

import com.example.myapi.modeldata.DataSiswa
import retrofit2.Response
import retrofit2.http.*

interface ServiceApiSiswa {

    @GET("bacateman.php")
    suspend fun getSiswa(): List<DataSiswa>

    @POST("insertTM.php")
    suspend fun postSiswa(@Body dataSiswa: DataSiswa): Response<Unit>

    // ==== FITUR BARU (DETAIL / UPDATE / DELETE) ====

    @GET("bacasatuteman.php")
    suspend fun getSatuSiswa(@Query("id") id: Int): DataSiswa

    @PUT("editTM.php")
    suspend fun updateSiswa(
        @Query("id") id: Int,
        @Body dataSiswa: DataSiswa
    ): Response<Unit>

    @DELETE("deleteTM.php")
    suspend fun deleteSiswa(@Query("id") id: Int): Response<Unit>
}
