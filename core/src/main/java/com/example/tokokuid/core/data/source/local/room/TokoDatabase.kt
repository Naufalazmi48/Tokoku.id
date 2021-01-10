package com.example.tokokuid.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tokokuid.core.data.source.local.entity.CartEntity
import com.example.tokokuid.core.data.source.local.entity.CityEntity

@Database(entities = [CartEntity::class,CityEntity::class],version = 1,exportSchema = false)
abstract class TokoDatabase: RoomDatabase(){
    abstract fun tokoDAO(): TokoDAO
}