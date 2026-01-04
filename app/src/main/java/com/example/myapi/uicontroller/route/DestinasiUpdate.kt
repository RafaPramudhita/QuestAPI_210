package com.example.myapi.uicontroller.route

import com.example.myapi.R

object DestinasiUpdate : DestinasiNavigasi {
    override val route = "item_update"
    override val titleRes = R.string.update_siswa

    const val itemIdArg = "id"
    val routeWithArgs = "$route/{$itemIdArg}"
}
