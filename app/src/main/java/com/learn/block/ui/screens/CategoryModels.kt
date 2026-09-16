package com.learn.block.ui.screens

data class CategoryFilter(val id: Int, val name: String, val count: Int)

data class CategoryBanner(
    val id: String,
    val catCode: String,
    val title: String,
    val itemsCount: String,
    val imageRes: Int,
    val isHighlighted: Boolean = false
)
