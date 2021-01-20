package com.example.tokokuid.core.domain.model


data class CostDomain (
        var service: String? = null,
        var description: String? = null,
        var cost: List<CostItemDomain?>? = null
        )

data class CostItemDomain(
        var value: Int? = null
)