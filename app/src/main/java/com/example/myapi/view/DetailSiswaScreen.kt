package com.example.myapi.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapi.R
import com.example.myapi.uicontroller.route.DestinasiDetail
import com.example.myapi.viewmodel.DetailViewModel
import com.example.myapi.viewmodel.StatusUiDetail
import com.example.myapi.viewmodel.provider.PenyediaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSiswaScreen(
    navigateBack: () -> Unit,
    navigateToUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val coroutineScope = rememberCoroutineScope()

    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SiswaTopAppBar(
                title = stringResource(R.string.detail_siswa),
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val id = viewModel.itemId
                    navigateToUpdate(id)
                },
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(Icons.Default.Edit, contentDescription = stringResource
                    (R.string.update_siswa))
            }
        }
    ) { innerPadding ->

        when (val state = viewModel.statusUiDetail) {
            is StatusUiDetail.Loading -> {
                Box(Modifier.padding(innerPadding).fillMaxSize()) {
                    CircularProgressIndicator()
                }
            }

            is StatusUiDetail.Error -> {
                Column(
                    Modifier.padding(innerPadding).fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(stringResource(R.string.gagal))
                    Spacer(Modifier.height(12.dp))
                    Button(onClick = viewModel::loadDetail) {
                        Text(stringResource(R.string.retry))
                    }
                }
            }

            is StatusUiDetail.Success -> {
                val siswa = state.siswa

                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(text = siswa.nama, style = MaterialTheme.typography.titleLarge)
                    Text(text = siswa.alamat, style = MaterialTheme.typography.titleMedium)
                    Text(text = siswa.telpon, style = MaterialTheme.typography.titleMedium)

                    Spacer(Modifier.height(12.dp))

                    OutlinedButton(
                        onClick = { showDeleteDialog = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.delete))
                    }
                }

                if (showDeleteDialog) {
                    AlertDialog(
                        onDismissRequest = { showDeleteDialog = false },
                        title = { Text(stringResource(R.string.attention)) },
                        text = { Text(stringResource(R.string.tanya)) },
                        confirmButton = {
                            TextButton(onClick = {
                                showDeleteDialog = false
                                coroutineScope.launch {
                                    viewModel.hapusSatuSiswa()
                                    navigateBack()
                                }
                            }) { Text(stringResource(R.string.yes)) }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDeleteDialog = false }) {
                                Text(stringResource(R.string.no))
                            }
                        }
                    )
                }
            }
        }
    }
}
