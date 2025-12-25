package com.example.myapi.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapi.modeldata.DetailSiswa
import com.example.myapi.modeldata.UIStateSiswa
import com.example.myapi.modeldata.toDataSiswa
import com.example.myapi.repositori.RepositoriDataSiswa
import retrofit2.Response

class EntryViewModel(
    private val repositoryDataSiswa: RepositoriDataSiswa
) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    /* Validasi input */
    private fun validasiInput(
        uiState: DetailSiswa = uiStateSiswa.detailSiswa
    ): Boolean {
        return uiState.nama.isNotBlank() &&
                uiState.alamat.isNotBlank() &&
                uiState.telpon.isNotBlank()
    }

    /* Update state saat input berubah */
    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa = UIStateSiswa(
            detailSiswa = detailSiswa,
            isEntryValid = validasiInput(detailSiswa)
        )
    }


}
