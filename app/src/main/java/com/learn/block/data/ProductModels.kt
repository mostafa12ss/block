package com.learn.block.data

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("products") val products: List<ProductDTO>
)

data class ProductDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Double,
    @SerializedName("category") val category: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("images") val images: List<String>? = null,
    @SerializedName("brand") val brand: String? = null
)
