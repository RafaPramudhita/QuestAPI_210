package com.example.myapi.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapi.modeldata.DetailSiswa
import com.example.myapi.modeldata.UIStateSiswa
import com.example.myapi.modeldata.toDataSiswa
import com.example.myapi.modeldata.toUiStateSiswa
import com.example.myapi.repositori.RepositoriDataSiswa
import com.example.myapi.uicontroller.route.DestinasiUpdate
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class UpdateViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: RepositoriDataSiswa
) : ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[DestinasiUpdate.itemIdArg])

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    init {
        loadDataLama()
    }

    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return uiState.nama.isNotBlank() &&
                uiState.alamat.isNotBlank() &&
                uiState.telpon.isNotBlank()
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa = UIStateSiswa(
            detailSiswa = detailSiswa,
            isEntryValid = validasiInput(detailSiswa)
        )
    }

    private fun loadDataLama() {
        viewModelScope.launch {
            try {
                val siswa = repository.getSatuSiswa(itemId)
                uiStateSiswa = siswa.toUiStateSiswa(isEntryValid = true)
            } catch (_: IOException) {
            } catch (_: HttpException) {
            }
        }
    }

    suspend fun updateSiswa() {
        if (validasiInput()) {
            repository.updateSiswa(itemId, uiStateSiswa.detailSiswa.toDataSiswa())
        }
    }
}
