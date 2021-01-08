package com.example.tokokuid.core.utils

import com.example.tokokuid.core.data.source.local.entity.CartEntity
import com.example.tokokuid.core.domain.model.CartDomain
import com.example.tokokuid.core.modelpresentation.Item

object DataMapper {

    fun mapEntitiesToDomainCart(input: List<CartEntity>): List<CartDomain> =
        input.map {
            CartDomain(
                id_item = it.item_id,
                name_item = it.item_name,
                price_item = it.item_price,
                weight_item = it.item_weight,
                url_picture_item = it.item_picture,
                description = it.item_description
            )
        }

    fun mapDomainToEntitiesCart(it:CartDomain):CartEntity =
            CartEntity(
                item_id = it.id_item,
                item_name = it.name_item,
                item_price = it.price_item,
                item_weight = it.weight_item,
                item_picture = it.url_picture_item,
                item_description = it.description
            )

    fun mapPresentationToDomain(item:Item):CartDomain =
        CartDomain(
            id_item = item.id_item,
            name_item = item.name_item,
            price_item = item.price_item,
            weight_item = item.weight_item,
            url_picture_item = item.url_picture_item,
            description = item.description
        )

    fun mapDomainToPresentation(cartDomain:List<CartDomain>):List<Item> =
        cartDomain.map { cart ->
            Item(
                id_item = cart.id_item,
                name_item = cart.name_item,
                price_item = cart.price_item,
                weight_item = cart.weight_item,
                url_picture_item = cart.url_picture_item,
                description = cart.description
            )
        }
}