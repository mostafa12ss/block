package com.learn.block.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.block.R

@Composable
fun ShopScreen(
    onCategoryClick: (String) -> Unit = {}
) {
    var selectedTopIndex by remember { mutableIntStateOf(2) }
    var selectedCategoryIndex by remember { mutableIntStateOf(0) }

    val categories = remember {
        listOf(
            CategoryFilter(1, "ALL", 142),
            CategoryFilter(2, "OUTERWEAR", 34),
            CategoryFilter(3, "SHIRTS", 28),
            CategoryFilter(4, "PANTS", 45),
            CategoryFilter(5, "ACCESSORIES", 35)
        )
    }

    val bannerCategories = remember {
        listOf(
            CategoryBanner("1", "CAT. 01", "OUTERWEAR", "34 ITEMS", R.drawable.shop1, isHighlighted = true),
            CategoryBanner("2", "CAT. 02", "SHIRTS", "56 ITEMS", R.drawable.shop2),
            CategoryBanner("3", "CAT. 03", "DENIM", "28 ITEMS", R.drawable.shop3)
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.Neutral)),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(R.color.Secondary))
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.forgedark),
                        contentDescription = null,
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "FORGE",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HeaderIconButton(
                        icon = Icons.Default.Search,
                        isSelected = selectedTopIndex == 0,
                        onClick = { selectedTopIndex = if (selectedTopIndex == 0) -1 else 0 }
                    )
                    HeaderIconButton(
                        icon = Icons.Default.Notifications,
                        isSelected = selectedTopIndex == 1,
                        onClick = { selectedTopIndex = if (selectedTopIndex == 1) -1 else 1 }
                    )
                    HeaderIconButton(
                        icon = Icons.Default.Person,
                        isSelected = selectedTopIndex == 2,
                        onClick = { selectedTopIndex = if (selectedTopIndex == 2) -1 else 2 }
                    )
                }
            }
        }

        item { Spacer(Modifier.height(20.dp)) }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                itemsIndexed(categories) { index, category ->
                    val isSelected = index == selectedCategoryIndex

                    if (isSelected) {
                        Box(modifier = Modifier.padding(end = 4.dp, bottom = 4.dp)) {
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .offset(x = 4.dp, y = 4.dp)
                                    .background(Color(0xFF002699))
                            )
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFF0044FF))
                                    .clickable { selectedCategoryIndex = index }
                                    .padding(horizontal = 14.dp, vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${category.name} / ${category.count}",
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 14.sp,
                                    color = Color.Black
                                )
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .background(colorResource(R.color.Neutral))
                                .border(width = 1.dp, color = Color.White.copy(alpha = 0.2f))
                                .clickable { selectedCategoryIndex = index }
                                .padding(horizontal = 14.dp, vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${category.name} / ${category.count}",
                                fontFamily = FontFamily.Monospace,
                                fontSize = 14.sp,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(16.dp)) }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = Color.White.copy(0.2f))
            )
        }

        item { Spacer(Modifier.height(20.dp)) }

        itemsIndexed(bannerCategories) { _, banner ->
            CategoryBannerCard(banner = banner, onClick = { onCategoryClick(banner.title) })
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
private fun CategoryBannerCard(banner: CategoryBanner, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clickable { onClick() }
    ) {
        if (banner.isHighlighted) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .offset(x = 6.dp, y = 6.dp)
                    .background(colorResource(R.color.Primary))
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.2f)
                .background(colorResource(R.color.Neutral))
                .border(width = 1.dp, color = colorResource(R.color.Tertiary))
        ) {
            Image(
                painter = painterResource(banner.imageRes),
                contentDescription = banner.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {

                    Text(
                        text = banner.catCode,
                        color = if (banner.isHighlighted) {
                            colorResource(R.color.Primary)
                        } else {
                            Color.White.copy(0.5f)
                        },
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 14.sp
                    )


                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = banner.title,
                        color = Color.White.copy(0.85f),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )
                    Text(
                        text = banner.itemsCount,
                        color = Color.White.copy(0.4f),
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun HeaderIconButton(
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = if (isSelected) {
                    colorResource(R.color.Primary)
                } else {
                    colorResource(R.color.Secondary)
                }
            )
            .border(width = 1.dp, color = Color.White.copy(alpha = 0.2f))
            .clickable(onClick = onClick)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isSelected) Color.Black else Color.White,
            modifier = Modifier.size(18.dp)
        )
    }
}

@Preview
@Composable
fun ShopScreenPreview() {
    ShopScreen()
}
