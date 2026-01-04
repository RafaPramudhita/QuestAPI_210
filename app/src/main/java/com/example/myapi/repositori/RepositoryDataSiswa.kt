package com.example.myapi.repositori

import com.example.myapi.apiservice.ServiceApiSiswa
import com.example.myapi.modeldata.DataSiswa
import retrofit2.Response

interface RepositoriDataSiswa {
    suspend fun getDataSiswa(): List<DataSiswa>
    suspend fun postDataSiswa(dataSiswa: DataSiswa): Response<Unit>

    // ==== FITUR BARU (DETAIL / UPDATE / DELETE) ====
    suspend fun getSatuSiswa(id: Int): DataSiswa
    suspend fun updateSiswa(id: Int, dataSiswa: DataSiswa): Response<Unit>
    suspend fun deleteSiswa(id: Int): Response<Unit>
}

class JaringanRepositoryDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
) : RepositoriDataSiswa {

    override suspend fun getDataSiswa(): List<DataSiswa> =
        serviceApiSiswa.getSiswa()

    override suspend fun postDataSiswa(dataSiswa: DataSiswa): Response<Unit> =
        serviceApiSiswa.postSiswa(dataSiswa)

    override suspend fun getSatuSiswa(id: Int): DataSiswa =
        serviceApiSiswa.getSatuSiswa(id)

    override suspend fun updateSiswa(id: Int, dataSiswa: DataSiswa): Response<Unit> =
        serviceApiSiswa.updateSiswa(id, dataSiswa)

    override suspend fun deleteSiswa(id: Int): Response<Unit> =
        serviceApiSiswa.deleteSiswa(id)
}
