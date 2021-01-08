package com.example.tokokuid.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tokokuid.core.data.source.local.entity.CartEntity

@Database(entities = [CartEntity::class],version = 1,exportSchema = false)
abstract class TokoDatabase: RoomDatabase(){
    abstract fun tokoDAO(): TokoDAO
}