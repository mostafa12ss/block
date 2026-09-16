package com.learn.block.data

import androidx.compose.runtime.mutableStateListOf
import com.learn.block.ui.component.ForgeProduct

object SavedProductsManager {
    val savedProducts = mutableStateListOf<ForgeProduct>()

    fun addProduct(product: ForgeProduct) {
        if (!savedProducts.any { it.title == product.title }) {
            savedProducts.add(product)
        }
    }

    fun removeProduct(product: ForgeProduct) {
        savedProducts.removeAll { it.title == product.title }
    }
}
