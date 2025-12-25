package com.example.myapi.repositori

import com.example.myapi.apiservice.ServiceApiSiswa
import com.example.myapi.modeldata.DataSiswa
import retrofit2.Response

interface RepositoriDataSiswa {

    suspend fun getDataSiswa(): List<DataSiswa>

    suspend fun postDataSiswa(
        dataSiswa: DataSiswa
    ): Response<Unit>
}

class JaringanRepositoryDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
) : RepositoriDataSiswa {

    override suspend fun getDataSiswa(): List<DataSiswa> =
        serviceApiSiswa.getSiswa()

    override suspend fun postDataSiswa(
        dataSiswa: DataSiswa
    ): Response<Unit> =
        serviceApiSiswa.postSiswa(dataSiswa)
}
