package com.example.tokokuid.core.modelpresentation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val name_item: String? = null,
    val price_item: Int? = null,
    val weight_item: Int? = null,
    val url_picture_item: Int? = null,
    val description: String? = null
):Parcelable
