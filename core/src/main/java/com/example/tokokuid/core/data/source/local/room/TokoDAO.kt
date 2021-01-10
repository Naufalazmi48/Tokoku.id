package com.example.tokokuid.core.data.source.local.room

import androidx.room.*
import com.example.tokokuid.core.data.source.local.entity.CartEntity
import com.example.tokokuid.core.data.source.local.entity.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TokoDAO {

    @Query("SELECT * FROM cart")
    fun getAllInCart(): Flow<List<CartEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cart: CartEntity)

    @Query("DELETE FROM cart WHERE item_id =:id")
    suspend fun deleteFromCart(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(tourism: List<CityEntity>)

    @Query("SELECT * FROM city")
    fun getCity(): Flow<List<CityEntity>>
}