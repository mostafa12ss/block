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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.block.R
import com.learn.block.data.ProductApi
import com.learn.block.data.SavedProductsManager
import com.learn.block.ui.component.ForgeProduct
import com.learn.block.ui.component.ProductCard
import com.learn.block.ui.component.sdp
import kotlinx.coroutines.launch

@Composable
fun CatageroDetals(
    onBack: () -> Unit = {},
    onProductClick: (ForgeProduct) -> Unit = {}
){
    val categories = remember {
        listOf(
            CategoryFilter(1, "ALL", 34),
            CategoryFilter(2, "OUTERWEAR", 12),
            CategoryFilter(3, "HEAVY", 28),
            CategoryFilter(5, "ACCESSORIES", 35)
        )
    }

    var selectedCategoryIndex by remember { mutableIntStateOf(0) }

    // API Data State
    var productsState by remember { mutableStateOf<List<ForgeProduct>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val api = ProductApi.create()
                val response = api.getMensShirts()
                productsState = response.products.map { dto ->
                    ForgeProduct(
                        title = dto.title.uppercase(),
                        category = "${dto.brand ?: "SYSTEM"} // ${dto.category.uppercase()}",
                        price = "$${dto.price}",
                        image = dto.thumbnail,
                        tag = if (dto.price > 50) "PREMIUM" else "CORE"
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.Neutral))
    ){
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(R.color.Secondary).copy(0.4f)),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = colorResource(R.color.white),
                    modifier = Modifier
                        .height(45.dp)
                        .width(60.dp)
                        .padding(start = 10.dp, end = 20.dp)
                        .clickable(onClick = onBack)
                )
                Image(
                    painter = painterResource(R.drawable.forgedark),
                    contentDescription = null,
                    Modifier
                        .size(45.dp)
                        .padding(end = 10.dp)
                )
                Text(
                    text = "PRODUCT DETAILS",
                    color = colorResource(R.color.white),
                    fontSize = 20.sp
                )
            }
        }
        item {
            Row(
                Modifier.fillMaxWidth().padding(start = 20.dp, end = 5.dp,top=10.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    Modifier.padding(top=5.dp).size(5.dp).background(colorResource(R.color.Primary))
                )
                Text(
                    text = "CATALOG // ARCHIVE",
                    color = colorResource(R.color.white).copy(0.6f),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Spacer(Modifier.width(10.dp))
                Box(
                    Modifier.background(color = colorResource(R.color.Secondary), shape = RoundedCornerShape(5.dp)).padding(start = 10.dp,end=10.dp,top=10.dp, bottom = 10.dp),

                ){
                    Box(
                        Modifier.size(10.dp).background(colorResource(R.color.Primary), shape = CircleShape).padding(start = 10.dp,end=10.dp).align(Alignment.CenterStart)
                    )
                        Text(
                            text = "SYS.ONLINE",
                            color = colorResource(R.color.white),
                            fontSize = 16.sp,
                            modifier = Modifier.align(Alignment.CenterEnd).padding(start = 20.dp,end=10.dp),
                        )
                }
            }
        }
        item {
            Box(
                Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp,top=10.dp, bottom = 10.dp).background(colorResource(R.color.Secondary).copy(0.5f), shape = RoundedCornerShape(5.dp)),
            ){
                Column(
                    Modifier.padding(all=10.dp).align(Alignment.CenterStart)
                ){
                    Row(Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "OUTERWEAR ARCHIVE",
                            color = colorResource(R.color.white).copy(0.8F),
                            fontSize = 18.sp,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = "[34 UNITS]",
                            color = colorResource(R.color.Primary),
                            fontSize = 18.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "Engineered thermal protection, multi-layer" +
                                "\n"+"membranes, and brutalist tailoring for severe"+
                                "metropolitan climates.",
                        color = colorResource(R.color.white).copy(0.4F),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }
        }
        item {
                LazyRow(
                    modifier = Modifier.padding(top=5.dp,end=10.dp),
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
            val scale = 1f

            if (isLoading) {
                Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = colorResource(R.color.Primary))
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 9.sdp(scale))
                ) {
                    productsState.chunked(2).forEach { rowProducts ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(7.sdp(scale))
                        ) {
                            rowProducts.forEach { product ->
                                ProductCard(
                                    product = product,
                                    scale = scale,
                                    modifier = Modifier.weight(1f),
                                    onFavoriteClick = { SavedProductsManager.addProduct(it) },
                                    onClick = { onProductClick(it) }
                                )
                            }
                            if (rowProducts.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                        Spacer(modifier = Modifier.height(7.sdp(scale)))
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun CatageroDetalsPreview(){
    CatageroDetals()
}
