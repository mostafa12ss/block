package com.learn.block.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

object ForgeDesign {
    val Background = Color(0xFF080808)
    val Surface = Color(0xFF111111)
    val Surface2 = Color(0xFF151515)
    val Border = Color(0xFF292929)
    val Primary = Color(0xFF0052FF)
    val White = Color.White
    val Muted = Color.White.copy(alpha = 0.55f)
    val Faint = Color.White.copy(alpha = 0.35f)
    const val DESIGN_WIDTH = 390f
}

fun Number.sdp(scale: Float): Dp = (toFloat() * scale).dp
fun Number.ssp(scale: Float): TextUnit = (toFloat() * scale).sp

data class ForgeProduct(
    val title: String,
    val category: String,
    val price: String,
    val image: String,
    val tag: String
)

@Composable
fun ProductCard(
    product: ForgeProduct,
    scale: Float,
    modifier: Modifier = Modifier,
    onFavoriteClick: (ForgeProduct) -> Unit = {},
    onClick: (ForgeProduct) -> Unit = {}
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(3.sdp(scale)))
            .background(ForgeDesign.Surface2)
            .border(1.dp, ForgeDesign.Border, RoundedCornerShape(3.sdp(scale)))
            .clickable { onClick(product) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.78f)
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.15f),
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.55f)
                            )
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(5.sdp(scale))
                    .background(Color.Black.copy(alpha = 0.78f))
                    .padding(horizontal = 5.sdp(scale), vertical = 3.sdp(scale))
            ) {
                Text(
                    text = product.tag,
                    color = Color.White,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.ssp(scale),
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(5.sdp(scale))
                    .size(24.sdp(scale))
                    .background(Color.Black.copy(alpha = 0.70f), RoundedCornerShape(2.sdp(scale)))
                    .border(1.dp, ForgeDesign.Border, RoundedCornerShape(2.sdp(scale)))
                    .clickable { onFavoriteClick(product) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.6f),
                    modifier = Modifier.size(12.sdp(scale))
                )
            }
        }

        Column(
            modifier = Modifier.padding(horizontal = 7.sdp(scale), vertical = 7.sdp(scale))
        ) {
            Text(
                text = product.title,
                color = Color.White,
                fontSize = 12.ssp(scale),
                fontWeight = FontWeight.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(5.sdp(scale)))
            Text(
                text = product.category,
                color = ForgeDesign.Faint,
                fontFamily = FontFamily.Monospace,
                fontSize = 8.ssp(scale),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(7.sdp(scale)))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.price,
                    color = Color.White,
                    fontSize = 11.ssp(scale),
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Box(
                    modifier = Modifier
                        .size(22.sdp(scale))
                        .clip(RoundedCornerShape(2.sdp(scale)))
                        .border(1.dp, ForgeDesign.Border, RoundedCornerShape(2.sdp(scale)))
                        .clickable {},
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 12.ssp(scale)
                    )
                }
            }
        }
    }
}
