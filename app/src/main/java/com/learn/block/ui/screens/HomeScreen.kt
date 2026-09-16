package com.learn.block.ui.screens

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learn.block.R
import com.learn.block.data.ProductApi
import com.learn.block.data.SavedProductsManager
import com.learn.block.ui.component.ForgeDesign
import com.learn.block.ui.component.ForgeProduct
import com.learn.block.ui.component.ProductCard
import com.learn.block.ui.component.sdp
import com.learn.block.ui.component.ssp
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onProductClick: (ForgeProduct) -> Unit = {}
) {
    val scale = rememberForgeScale()
    var selectedButton by remember { mutableIntStateOf(2) }
    var selectedCategory by remember { mutableIntStateOf(0) }
    
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

    val infiniteTransition = rememberInfiniteTransition(label = "ForgeAnimation")
    val animatedBlue by infiniteTransition.animateColor(
        targetValue = ForgeDesign.Primary,
        initialValue = Color(0xFF000000),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1400, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "BlueAnimation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ForgeDesign.Background)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 520.dp),
            contentPadding = PaddingValues(bottom = 30.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.sdp(scale))
                        .background(ForgeDesign.Background),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.sdp(scale))
                            .clip(CircleShape)
                            .background(animatedBlue)
                    )
                    Spacer(modifier = Modifier.width(7.sdp(scale)))
                    Text(
                        text = "COMPLIMENTARY GLOBAL FREIGHT ON ORDERS OVER $300",
                        color = ForgeDesign.Muted,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 8.5.ssp(scale),
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(ForgeDesign.Surface)
                        .padding(horizontal = 12.sdp(scale), vertical = 9.sdp(scale)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.forgedark),
                            contentDescription = "Forge",
                            modifier = Modifier.size(30.sdp(scale)),
                            contentScale = ContentScale.Fit
                        )
                        Spacer(modifier = Modifier.width(9.sdp(scale)))
                        Column {
                            Text(
                                text = "FORGE",
                                color = Color.White,
                                fontSize = 14.ssp(scale),
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.ssp(scale)
                            )
                            Text(
                                text = "S Y S T E M S",
                                color = ForgeDesign.Faint,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 6.5.ssp(scale),
                                letterSpacing = 1.5.ssp(scale)
                            )
                        }
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(2.sdp(scale))) {
                        val icons = listOf(Icons.Default.Search, Icons.Default.Notifications, Icons.Default.Person)
                        icons.forEachIndexed { index, icon ->
                            val selected = selectedButton == index
                            Box(
                                modifier = Modifier
                                    .size(34.sdp(scale))
                                    .clip(RoundedCornerShape(5.sdp(scale)))
                                    .background(if (selected) Color(0xFF292929) else Color.Transparent)
                                    .clickable { selectedButton = index },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    tint = if (selected) Color.White else Color.White.copy(alpha = 0.45f),
                                    modifier = Modifier.size(17.sdp(scale))
                                )
                            }
                        }
                    }
                }
            }

            item {
                BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                    val heroHeight = (maxWidth.value * 1.25f).coerceIn(390f, 520f).dp
                    Box(modifier = Modifier.fillMaxWidth().height(heroHeight)) {
                        Image(
                            painter = painterResource(R.drawable.screenm),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.TopCenter
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Black.copy(alpha = 0.10f),
                                            Color.Black.copy(alpha = 0.20f),
                                            ForgeDesign.Background.copy(alpha = 0.98f)
                                        )
                                    )
                                )
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.sdp(scale), vertical = 10.sdp(scale)),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TacticalLabel(text = "COORD: 45.421°N // FLD-TESTED", scale = scale, animatedBlue = animatedBlue)
                            TacticalLabel(text = "DROP // 2024.AW_01", scale = scale)
                        }
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .fillMaxWidth()
                                .padding(horizontal = 14.sdp(scale), vertical = 14.sdp(scale))
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(3.sdp(scale)))
                                    .background(Color.Black.copy(alpha = 0.82f))
                                    .border(width = 1.dp, color = ForgeDesign.Primary, shape = RoundedCornerShape(3.sdp(scale)))
                                    .padding(horizontal = 8.sdp(scale), vertical = 5.sdp(scale))
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(modifier = Modifier.size(5.sdp(scale)).clip(CircleShape).background(animatedBlue))
                                    Spacer(modifier = Modifier.width(6.sdp(scale)))
                                    Text(
                                        text = "SYSTEMS CAPSULE 01",
                                        color = Color.White,
                                        fontFamily = FontFamily.Monospace,
                                        fontSize = 8.5.ssp(scale),
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = 0.7.ssp(scale)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(9.sdp(scale)))
                            Text(
                                text = "STRUCTURED\nELEMENTS",
                                color = Color.White,
                                fontSize = 28.ssp(scale),
                                lineHeight = 28.ssp(scale),
                                fontWeight = FontWeight.Black,
                                letterSpacing = 0.2.ssp(scale)
                            )
                            Spacer(modifier = Modifier.height(6.sdp(scale)))
                            Text(
                                text = "Architectural outerwear engineered for cold-\nweather metropolitan transitions and severe\natmospheric exposure.",
                                color = Color.White.copy(alpha = 0.68f),
                                fontSize = 9.5.ssp(scale),
                                lineHeight = 13.ssp(scale),
                                maxLines = 3
                            )
                            Spacer(modifier = Modifier.height(12.sdp(scale)))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.sdp(scale))
                            ) {
                                HeroButton(text = "EXPLORE DROP", primary = true, scale = scale, modifier = Modifier.weight(1f))
                                HeroButton(text = "LOOKBOOK [AW24]", primary = false, scale = scale, modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(1.dp))
                CategoryBar(selectedCategory = selectedCategory, onCategorySelected = { selectedCategory = it }, scale = scale)
            }

            item { CatalogHeader(scale = scale) }

            item {
                if (isLoading) {
                    Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = ForgeDesign.Primary)
                    }
                } else {
                    ProductGrid(products = productsState, scale = scale, onProductClick = onProductClick)
                }
            }

            item { Spacer(modifier = Modifier.height(25.sdp(scale))) }
        }
    }
}

@Composable
private fun TacticalLabel(text: String, scale: Float, animatedBlue: Color? = null) {
    Row(
        modifier = Modifier
            .background(Color.Black.copy(alpha = 0.62f), RoundedCornerShape(3.sdp(scale)))
            .padding(horizontal = 6.sdp(scale), vertical = 4.sdp(scale)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (animatedBlue != null) {
            Box(modifier = Modifier.size(5.sdp(scale)).clip(CircleShape).background(animatedBlue))
            Spacer(modifier = Modifier.width(5.sdp(scale)))
        }
        Text(
            text = text,
            color = Color.White.copy(alpha = 0.75f),
            fontFamily = FontFamily.Monospace,
            fontSize = 6.5.ssp(scale),
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    }
}

@Composable
private fun HeroButton(text: String, primary: Boolean, scale: Float, modifier: Modifier) {
    Row(
        modifier = modifier
            .height(38.sdp(scale))
            .clip(RoundedCornerShape(3.sdp(scale)))
            .background(if (primary) ForgeDesign.Primary else ForgeDesign.Surface2)
            .border(width = if (primary) 0.dp else 1.dp, color = ForgeDesign.Border, shape = RoundedCornerShape(3.sdp(scale)))
            .clickable {},
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = Color.White,
            fontFamily = FontFamily.Monospace,
            fontSize = 12.ssp(scale),
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
        if (primary) {
            Spacer(modifier = Modifier.width(5.sdp(scale)))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(11.sdp(scale))
            )
        }
    }
}

@Composable
private fun CategoryBar(selectedCategory: Int, onCategorySelected: (Int) -> Unit, scale: Float) {
    val categories = listOf("ALL ARCHIVE", "OUTERWEAR", "TECH SHIRTS")
    Column {
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.White.copy(alpha = 0.18f)))
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 9.sdp(scale), vertical = 8.sdp(scale)),
            horizontalArrangement = Arrangement.spacedBy(6.sdp(scale))
        ) {
            categories.forEachIndexed { index, category ->
                val selected = selectedCategory == index
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(29.sdp(scale))
                        .clip(RoundedCornerShape(3.sdp(scale)))
                        .background(if (selected) ForgeDesign.Primary else ForgeDesign.Surface2)
                        .border(width = if (selected) 0.dp else 1.dp, color = ForgeDesign.Border, shape = RoundedCornerShape(3.sdp(scale)))
                        .clickable { onCategorySelected(index) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = category,
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 10.ssp(scale),
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                }
            }
        }
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.White.copy(alpha = 0.18f)))
    }
}

@Composable
private fun CatalogHeader(scale: Float) {
    Column(modifier = Modifier.fillMaxWidth().padding(start = 10.sdp(scale), end = 10.sdp(scale), top = 17.sdp(scale))) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(7.sdp(scale)).background(ForgeDesign.Primary))
            Spacer(modifier = Modifier.width(7.sdp(scale)))
            Text(
                text = "CATALOGUE // 01",
                color = ForgeDesign.Primary,
                fontFamily = FontFamily.Monospace,
                fontSize = 8.ssp(scale),
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(7.sdp(scale)))
        Text(text = "THE ARCHIVE // CURATED", color = Color.White, fontSize = 21.ssp(scale), fontWeight = FontWeight.ExtraBold)
        Spacer(modifier = Modifier.height(5.sdp(scale)))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Laboratory prototypes & ready units",
                color = ForgeDesign.Faint,
                fontFamily = FontFamily.Monospace,
                fontSize = 8.5.ssp(scale),
                maxLines = 1
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "VIEW ALL", color = Color.White, fontFamily = FontFamily.Monospace, fontSize = 9.5.ssp(scale), fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(3.sdp(scale)))
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = Color.White, modifier = Modifier.size(10.sdp(scale)))
            }
        }
        Spacer(modifier = Modifier.height(12.sdp(scale)))
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.White.copy(alpha = 0.18f)))
    }
}

@Composable
private fun ProductGrid(products: List<ForgeProduct>, scale: Float, onProductClick: (ForgeProduct) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 9.sdp(scale))) {
        products.chunked(2).forEach { rowProducts ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(7.sdp(scale))) {
                rowProducts.forEach { product ->
                    ProductCard(
                        product = product,
                        scale = scale,
                        modifier = Modifier.weight(1f),
                        onFavoriteClick = { SavedProductsManager.addProduct(it) },
                        onClick = { onProductClick(product) }
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

@Composable
private fun rememberForgeScale(): Float {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.toFloat()
    return (screenWidth / ForgeDesign.DESIGN_WIDTH).coerceIn(0.85f, 1.25f)
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
