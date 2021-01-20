package com.example.tokokuid.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "cityId")
    var cityId:String?,
    @ColumnInfo(name = "cityName")
    var cityName:String?
){
    constructor(cityId: String?,cityName: String?):this(
        0,cityId,cityName
    )
}
