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
    var cityName:String?,
    @ColumnInfo(name = "provinceId")
    var provinceId:String?,
    @ColumnInfo(name = "provinceName")
    var provinceName:String?,
    @ColumnInfo(name = "type")
    var type:String?,
    @ColumnInfo(name = "postalCode")
    var postalCode:String?,
){
    constructor(cityId: String?,cityName: String?,provinceId: String?,provinceName: String?,type: String?,postalCode: String?):this(
        0,cityId,cityName,provinceId,provinceName,type,postalCode
    )
}
