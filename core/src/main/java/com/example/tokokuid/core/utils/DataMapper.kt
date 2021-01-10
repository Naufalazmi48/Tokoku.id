package com.example.tokokuid.core.utils

import com.example.tokokuid.core.data.source.local.entity.CartEntity
import com.example.tokokuid.core.data.source.local.entity.CityEntity
import com.example.tokokuid.core.data.source.remote.response.CityResponse
import com.example.tokokuid.core.data.source.remote.response.CostResponse
import com.example.tokokuid.core.domain.model.CartDomain
import com.example.tokokuid.core.domain.model.CityDomain
import com.example.tokokuid.core.domain.model.CostDomain
import com.example.tokokuid.core.domain.model.CostItemDomain
import com.example.tokokuid.core.modelpresentation.Item
import com.example.tokokuid.core.modelpresentation.City

object DataMapper {

    fun mapItemEntitiesToDomainCart(input: List<CartEntity>): List<CartDomain> =
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

    fun mapItemDomainToEntitiesCart(it: CartDomain): CartEntity =
        CartEntity(
            item_id = it.id_item,
            item_name = it.name_item,
            item_price = it.price_item,
            item_weight = it.weight_item,
            item_picture = it.url_picture_item,
            item_description = it.description
        )

    fun mapItemPresentationToDomain(item: Item): CartDomain =
        CartDomain(
            id_item = item.id_item,
            name_item = item.name_item,
            price_item = item.price_item,
            weight_item = item.weight_item,
            url_picture_item = item.url_picture_item,
            description = item.description
        )

    fun mapItemDomainToPresentation(cartDomain: List<CartDomain>): List<Item> =
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

    fun mapCityResponseToEntities(city: CityResponse): List<CityEntity> {
        val listCity = ArrayList<CityEntity>()
        city.rajaongkir?.results?.let { list ->
            list.map {
                listCity.add(
                    CityEntity(
                        it?.cityName,
                        it?.province,
                        it?.provinceId,
                        it?.type,
                        it?.postalCode,
                        it?.cityId
                    )
                )
            }
        }
        return listCity
    }

    fun mapCityDomainToPresentation(list: List<CityDomain>?): List<City> {
        val listCity = ArrayList<City>()
        list?.map {
            listCity.add(
                City(
                    it.cityName,
                    it.cityId
                )
            )
        }
        return listCity
    }

    fun mapCityEntitiesToDomain(city: List<CityEntity>): List<CityDomain> {
        val listCity = ArrayList<CityDomain>()
        city.map {
            listCity.add(
                CityDomain(
                    it.cityId,
                    it.cityName,
                    it.provinceId,
                    it.provinceName,
                    it.type,
                    it.postalCode
                )
            )
        }
        return listCity
    }

    fun mapCostResponseToDomain(cost: CostResponse): CostDomain {
        val costDomain = CostDomain()
        cost.rajaongkir?.results?.first()?.costs?.map {
            costDomain.service = it?.service
            costDomain.description = it?.service
            val listCostItemDomain = ArrayList<CostItemDomain>()
            it?.cost?.map { costItem ->
                listCostItemDomain.add(
                    CostItemDomain(
                        costItem?.note,
                        costItem?.etd,
                        costItem?.value
                    )
                )
            }
            costDomain.cost = listCostItemDomain
        }
        return costDomain
    }
}