package com.example.tokokuid.core.modelpresentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val id_item:Int,
    val name_item: String,
    val price_item: Int,
    val weight_item: Int,
    val url_picture_item: Int,
    val description: String
):Parcelable{
    constructor(name:String,price:Int,weight:Int,picture:Int,description:String) : this(
        id_item = 0,
        name_item = name,
        price_item = price,
        weight_item = weight,
        url_picture_item = picture,
        description = description
    )
}
