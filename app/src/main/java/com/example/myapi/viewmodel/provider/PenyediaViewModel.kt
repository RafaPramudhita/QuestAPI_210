package com.example.myapi.viewmodel.provider

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapi.repositori.AplikasiDataSiswa
import com.example.myapi.viewmodel.*

fun CreationExtras.aplikasiDataSiswa(): AplikasiDataSiswa =
    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiDataSiswa

object PenyediaViewModel {

    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(aplikasiDataSiswa().container.repositoryDataSiswa)
        }

        initializer {
            EntryViewModel(aplikasiDataSiswa().container.repositoryDataSiswa)
        }

        initializer {
            DetailViewModel(
                savedStateHandle = createSavedStateHandle(),
                repository = aplikasiDataSiswa().container.repositoryDataSiswa
            )
        }

        initializer {
            UpdateViewModel(
                savedStateHandle = createSavedStateHandle(),
                repository = aplikasiDataSiswa().container.repositoryDataSiswa
            )
        }
    }
}
