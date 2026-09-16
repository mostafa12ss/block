package com.learn.block.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.learn.block.R
import com.learn.block.data.ProductApi
import com.learn.block.data.ProductDTO
import com.learn.block.ui.component.ImageCarouselCard
import kotlinx.coroutines.launch

@Composable
fun ProductDetiels(
    onBack: () -> Unit = {}
){
    var productState by remember { mutableStateOf<ProductDTO?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val api = ProductApi.create()
                val response = api.getMensShirts()
                productState = response.products.firstOrNull()
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
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        if (isLoading) {
            item {
                Box(modifier = Modifier.fillMaxWidth().height(400.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = colorResource(R.color.Primary))
                }
            }
        } else if (productState != null) {
            val product = productState!!
            
            item {
                Card(
                    Modifier
                        .fillMaxWidth()
                        .height(330.dp)
                ) {
                    val images = if (!product.images.isNullOrEmpty()) product.images!! else listOf(product.thumbnail)
                    ImageCarouselCard(images = images)
                }
            }
            
            item { 
                Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = product.title.uppercase().replace(" ", "\n"),
                        fontSize = 28.sp,
                        color = colorResource(R.color.white).copy(0.8f),
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 32.sp,
                        modifier = Modifier.weight(1f),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "$${product.price}",
                        fontSize = 20.sp,
                        color = colorResource(R.color.Primary),
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1
                    )
                  }
                }
                
            item {
                Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = product.category.uppercase(),
                        fontSize = 16.sp,
                        color = colorResource(R.color.white).copy(0.8f),
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier
                            .background(color = colorResource(R.color.black))
                            .border(
                                width = 1.dp,
                                color = colorResource(R.color.Secondary).copy(0.8f)
                            )
                            .padding(all = 10.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "RESTOCKED",
                        fontSize = 16.sp,
                        color = colorResource(R.color.white),
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier
                            .background(color = colorResource(R.color.Primary))
                            .padding(all = 10.dp),
                        maxLines = 1
                    )
                }
            }
            
            item {
                Spacer(Modifier.height(10.dp))
            }
            
            item {
                Box(
                    Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(color = colorResource(R.color.Tertiary))
                )
            }
            
            item {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "01 // DESIGN INTENT",
                        color = colorResource(R.color.white).copy(0.8f),
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier.padding(start = 20.dp, top = 20.dp)
                    )
                    Text(
                        text = product.description,
                        color = colorResource(R.color.white).copy(0.6f),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.padding(start = 20.dp, top = 12.dp, end = 20.dp)
                    )
                }
            }

            item { Spacer(Modifier.height(10.dp)) }
            item { BoxData(product) }
            item { Spacer(Modifier.height(10.dp)) }
            item { ChoseData() }
            item { Spacer(Modifier.height(10.dp)) }
            item { endData(product) }
        }
    }
}

@Composable
fun BoxData(product: ProductDTO){
Column (
    Modifier
        .fillMaxWidth()
        .background(color = colorResource(R.color.Secondary))
){
   Row() {  Box(
        Modifier
            .height(2.dp)
            .fillMaxWidth()
            .background(color = colorResource(R.color.white).copy(0.4f))
            .padding(bottom = 10.dp))}
Row() { Text(
    text = "02 // STRUCTURAL DATA",
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold,
    color = colorResource(R.color.white).copy(0.6f),
    modifier = Modifier.padding(top = 20.dp, bottom = 20.dp, start = 10.dp)
)
}

    StructuralRow("BRAND", product.brand ?: "FORGE SYSTEMS")
    StructuralRow("CATEGORY", product.category.uppercase())
    StructuralRow("MODEL ID", "FRG-${product.id}X")
    StructuralRow("AVAILABILITY", "READY TO SHIP")
    StructuralRow("WARRANTY", "LIFETIME SYSTEM")

    Spacer(Modifier.height(20.dp))
    Row() {  Box(
        Modifier
            .height(2.dp)
            .fillMaxWidth()
            .background(color = colorResource(R.color.white).copy(0.4f))
            .padding(bottom = 10.dp))
        }
    }
}

@Composable
fun StructuralRow(label: String, value: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = colorResource(R.color.white).copy(0.4f),
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        )
        Text(
            text = value,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = colorResource(R.color.white),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f, fill = false)
        )
    }
    Box(
        Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(color = colorResource(R.color.Tertiary).copy(0.6f))
    )
}

@Composable
fun ChoseData() {
    var selectedSize by remember { mutableStateOf("L") }
    val sizes = remember { listOf("XS", "S", "M", "L") }
    val sizes2= remember { listOf( "XL", "XXL") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "03// FIT PARAMETERS",
                color = colorResource(R.color.white).copy(0.6f),
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Column() {
                Text(
                    text = "SIZE GUIDE",
                    color = colorResource(R.color.Primary),
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Monospace,
                )
                Box(Modifier.height(1.dp).fillMaxWidth(0.43f).background(colorResource(R.color.Primary)))
            }
        }
        Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            sizes.forEach { size ->
                val isSelected = selectedSize == size
                Box(
                    modifier = Modifier
                        .background(colorResource(R.color.Neutral).copy(0.4f))
                        .border(
                            width = 1.dp,
                            color = if (isSelected) colorResource(R.color.Primary) else colorResource(R.color.Secondary)
                        )
                        .clickable { selectedSize = size }
                        .height(45.dp).width(80.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = size,
                        color =  colorResource(R.color.white).copy(0.7f),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
            sizes2.forEach { size ->
                val isSelected = selectedSize == size
                Box(
                    modifier = Modifier
                        .background(colorResource(R.color.Neutral).copy(0.4f))
                        .border(
                            width = 1.dp,
                            color = if (isSelected) colorResource(R.color.Primary) else colorResource(R.color.Secondary)
                        )
                        .clickable { selectedSize = size }
                        .height(45.dp).width(80.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = size,
                        color =  colorResource(R.color.white).copy(0.7f),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
      }
    }
}

@Composable
fun  endData(product: ProductDTO){
Column(
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    Text(
        text = "* Engineered for a technical, close-\nto-body fit. Size up if layering\nheavily.",
        color = colorResource(R.color.white).copy(0.5f),
        fontSize = 16.sp,
        fontFamily = FontFamily.Monospace,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
    Spacer(Modifier.height(20.dp))
    Box(Modifier.fillMaxWidth().height(250.dp)){
        AsyncImage(
            model = product.thumbnail,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
    Spacer(Modifier.height(20.dp))
    Box(Modifier.height(1.dp).fillMaxWidth().background(color = colorResource(R.color.Tertiary)))
    Spacer(Modifier.height(20.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 10.dp, bottom = 30.dp, end = 20.dp)
            .background(color = colorResource(R.color.Primary))
            .clickable(onClick = {}),
        contentAlignment = Alignment.CenterStart
    ){
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = null,
            tint = colorResource(R.color.white),
            modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
        )
        Text(
            text = "ADD TO GEAR -- $${product.price}",
            color = colorResource(R.color.white),
            fontSize = 18.sp,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
}

@Preview
@Composable
fun ProductDetielsPreview(){
    ProductDetiels()
}
