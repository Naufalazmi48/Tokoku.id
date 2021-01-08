package com.example.tokokuid.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "item_id")
    var item_id: Int = 0,
    @ColumnInfo(name = "item_name")
    var item_name:String,
    @ColumnInfo(name = "item_price")
    var item_price:Int,
    @ColumnInfo(name = "item_weight")
    var item_weight:Int,
    @ColumnInfo(name = "item_picture")
    var item_picture:Int,
    @ColumnInfo(name = "item_description")
    var item_description:String
){
    constructor(name:String,price:Int,weight:Int,picture:Int,description:String) : this(
        item_id = 0,
        item_name = name,
        item_price = price,
        item_weight = weight,
        item_picture = picture,
        item_description = description
    )
}