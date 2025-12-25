package com.example.myapi.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapi.modeldata.DataSiswa
import com.example.myapi.repositori.RepositoriDataSiswa
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface StatusUiSiswa {
    data class Success(
        val siswa: List<DataSiswa> = listOf()
    ) : StatusUiSiswa

    object Error : StatusUiSiswa
    object Loading : StatusUiSiswa
}

class HomeViewModel(
    private val repositoryDataSiswa: RepositoriDataSiswa
) : ViewModel() {

}
