    package com.example.myapi.repositori

    import android.app.Application
    import com.example.myapi.apiservice.ServiceApiSiswa
    import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
    import kotlinx.serialization.json.Json
    import okhttp3.MediaType.Companion.toMediaType
    import okhttp3.OkHttpClient
    import okhttp3.logging.HttpLoggingInterceptor
    import retrofit2.Retrofit


    interface ContainerApp {
        val repositoryDataSiswa: RepositoriDataSiswa
    }



