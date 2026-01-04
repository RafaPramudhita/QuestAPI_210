package com.example.myapi.uicontroller.route

import com.example.myapi.R

object DestinasiDetail : DestinasiNavigasi {
    override val route = "item_detail"
    override val titleRes = R.string.detail_siswa

    const val itemIdArg = "id"
    val routeWithArgs = "$route/{$itemIdArg}"
}
