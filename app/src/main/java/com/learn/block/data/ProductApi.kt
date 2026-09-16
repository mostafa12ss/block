package com.learn.block.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ProductApi {
    @GET("products/category/mens-shirts")
    suspend fun getMensShirts(): ProductResponse

    companion object {
        private const val BASE_URL = "https://dummyjson.com/"

        fun create(): ProductApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductApi::class.java)
        }
    }
}
