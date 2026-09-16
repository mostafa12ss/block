package com.learn.block.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import coil.compose.AsyncImage
import com.learn.block.R
import com.learn.block.data.SavedProductsManager
import com.learn.block.ui.component.ForgeDesign
import com.learn.block.ui.component.ForgeProduct
import com.learn.block.ui.component.sdp
import com.learn.block.ui.component.ssp

@Composable
fun SavedScreen() {
    val savedCount = SavedProductsManager.savedProducts.size
    
    // CategoryFilter is now provided by CategoryModels.kt in the same package
    val categories = remember(savedCount) {
        listOf<CategoryFilter>(
            CategoryFilter(1, "ALL", savedCount),
            CategoryFilter(2, "OUTERWEAR", 0),
            CategoryFilter(3, "HEAVY", 0),
            CategoryFilter(5, "ACCESSORIES", 0)
        )
    }

    val savedProducts = SavedProductsManager.savedProducts

    // Calculate total price
    val totalPrice = savedProducts.sumOf { 
        it.price.replace("$", "").toDoubleOrNull() ?: 0.0 
    }

    var selectedTopIndex by remember { mutableIntStateOf(2) }
    var selectedCategoryIndex by remember { mutableIntStateOf(0) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.Neutral)),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // --- Header ---
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

        item {
            Text(
                text = "ARCHIVE // SAVED",
                fontFamily = FontFamily.Monospace,
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 20.dp)
            )
        }

        item {
            LazyRow(
                modifier = Modifier.padding(top = 5.dp, end = 10.dp),
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
                                    text = "${category.name} [${category.count}]",
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
                                text = "${category.name} [${category.count}]",
                                fontFamily = FontFamily.Monospace,
                                fontSize = 14.sp,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
            }
        }

        item { Spacer(Modifier.height(10.dp)) }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .background(
                        color = colorResource(R.color.Secondary),
                        shape = RoundedCornerShape(5.dp),
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
                ) {
                    Text(
                        text = "TRANSFER READY UNITS",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        color = Color.White.copy(0.8f),
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$${"%.2f".format(totalPrice)}",
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Text(
                            text = "[${"%02d".format(savedProducts.size)} VERIFIED\nINVENTORY]",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 14.sp,
                            color = Color.White.copy(0.5f),
                        )
                    }
                }
                Box(
                    modifier = Modifier.padding(end = 10.dp, top = 10.dp, bottom = 10.dp).background(colorResource(R.color.Primary), shape = RoundedCornerShape(5.dp)).align(Alignment.CenterVertically).shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(5.dp),
                        spotColor = colorResource(R.color.Primary)
                    ).clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "TRANSFER\n  ALL",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp).align(Alignment.CenterStart).padding(end = 20.dp)
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(30.dp).padding(end = 10.dp).align(Alignment.CenterEnd)
                    )
                }
            }
        }

        item { Spacer(Modifier.height(10.dp)) }

        item {
            val scale = 1f
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 9.sdp(scale))
            ) {
                if (savedProducts.isEmpty()) {
                    Box(Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                        Text("NO SAVED ITEMS", color = Color.White.copy(alpha = 0.5f), fontFamily = FontFamily.Monospace)
                    }
                } else {
                    savedProducts.forEach { product ->
                        SavedProductCard(
                            product = product,
                            scale = scale,
                            modifier = Modifier.fillMaxWidth(),
                            onDelete = { SavedProductsManager.removeProduct(product) }
                        )
                        Spacer(modifier = Modifier.height(7.sdp(scale)))
                    }
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
                }, shape = RoundedCornerShape(5.dp)
            )
            .border(width = 1.dp, color = Color.White.copy(alpha = 0.2f), shape = RoundedCornerShape(5.dp))
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

@Composable
fun SavedProductCard(
    product: ForgeProduct,
    scale: Float,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.sdp(scale)))
            .background(ForgeDesign.Surface2)
            .border(
                1.dp,
                ForgeDesign.Border,
                RoundedCornerShape(4.sdp(scale))
            )
            .padding(8.sdp(scale))
    ) {
        Box(
            modifier = Modifier
                .size(90.sdp(scale))
                .clip(RoundedCornerShape(3.sdp(scale)))
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(10.sdp(scale)))

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(6.sdp(scale))
                            .background(
                                color = if (product.tag == "PREMIUM") Color(0xFF0052FF) else Color(0xFF34C759),
                                shape = androidx.compose.foundation.shape.CircleShape
                            )
                    )
                    Spacer(modifier = Modifier.width(5.sdp(scale)))
                    Text(
                        text = if (product.tag == "PREMIUM") "LIMITED STOCK" else "IN STOCK",
                        color = ForgeDesign.Muted,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 9.ssp(scale),
                        maxLines = 1
                    )
                }
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.6f),
                    modifier = Modifier.size(14.sdp(scale)).clickable { onDelete() }
                )
            }

            Spacer(modifier = Modifier.height(6.sdp(scale)))

            Text(
                text = product.title,
                color = Color.White,
                fontSize = 14.ssp(scale),
                fontWeight = FontWeight.Black,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(3.sdp(scale)))

            Text(
                text = product.category,
                color = ForgeDesign.Faint,
                fontFamily = FontFamily.Monospace,
                fontSize = 9.ssp(scale),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(6.sdp(scale)))
            Row(horizontalArrangement = Arrangement.spacedBy(6.sdp(scale))) {
                listOf(product.tag, "VERIFIED").forEach { tag ->
                    Box(
                        modifier = Modifier
                            .border(
                                1.dp,
                                ForgeDesign.Border,
                                RoundedCornerShape(2.sdp(scale))
                            )
                            .padding(horizontal = 5.sdp(scale), vertical = 2.sdp(scale))
                    ) {
                        Text(
                            text = tag,
                            color = ForgeDesign.Muted,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 8.ssp(scale)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.sdp(scale)))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.price,
                    color = Color.White,
                    fontSize = 15.ssp(scale),
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(3.sdp(scale)))
                        .background(ForgeDesign.Primary)
                        .clickable { }
                        .padding(horizontal = 12.sdp(scale), vertical = 6.sdp(scale)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.sdp(scale))
                ) {
                    Text(
                        text = "+",
                        color = Color.White,
                        fontSize = 12.ssp(scale),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "BAG",
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.ssp(scale),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SavedPreview(){
    SavedScreen()
}
