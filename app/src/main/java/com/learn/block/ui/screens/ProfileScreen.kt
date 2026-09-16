package com.learn.block.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.learn.block.R
import com.learn.block.data.ProductApi
import kotlinx.coroutines.launch

data class UserProfileData(
    val name: String,
    val role: String,
    val memberId: String,
    val location: String,
    val tier: String,
    val orders: String,
    val savedUnits: String,
    val credits: String,
    val deploymentTitle: String,
    val deploymentImage: String,
    val trackingNumber: String
)

@Composable
fun ProfilScreen(){
    var selectedTopIndex by remember { mutableIntStateOf(2) }
    var load = 0.8f
    var selectedCategoryIndex by remember { mutableIntStateOf(0) }

    // State management for dynamic profile data
    var profileData by remember {
        mutableStateOf(
            UserProfileData(
                name = "MARCUS V...",
                role = "ARCHITECTURAL LEAD & MEMBER",
                memberId = "SYS_8492",
                location = "BERLIN DOCK 04",
                tier = "TIER 01",
                orders = "14",
                savedUnits = "08",
                credits = "$240",
                deploymentTitle = "MODULAR 3L SHELL PARKA",
                deploymentImage = "",
                trackingNumber = "#FRG-9941-X"
            )
        )
    }
    var isLoading by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                // Fetch dynamic product context from the endpoint to bind real item properties to deployment
                val api = ProductApi.create()
                val response = api.getMensShirts()
                val product = response.products.firstOrNull()
                if (product != null) {
                    profileData = profileData.copy(
                        deploymentTitle = product.title.uppercase(),
                        deploymentImage = product.thumbnail,
                        orders = "${response.products.size + 4}",
                        credits = "$${(product.price * 2).toInt()}"
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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    AsyncImage(
                        model = profileData.deploymentImage.ifEmpty { R.drawable.forgedark },
                        contentDescription = null,
                        modifier = Modifier
                            .size(45.dp)
                            .padding(end = 10.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = "PROFILE",
                        color = colorResource(R.color.white),
                        fontSize = 20.sp
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 10.dp, top = 10.dp, bottom = 10.dp)
                ) {
                    HeaderIconButton(
                        icon = Icons.Default.Settings,
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
        item { Spacer(Modifier.height(10.dp)) }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                if (isLoading) {
                    Box(modifier = Modifier.fillMaxWidth().height(150.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = colorResource(R.color.Primary))
                    }
                } else {
                    MemberProfileCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .widthIn(max = 900.dp),
                        name = profileData.name,
                        role = profileData.role,
                        memberId = profileData.memberId,
                        location = profileData.location,
                        tier = profileData.tier,
                        orders = profileData.orders,
                        savedUnits = profileData.savedUnits,
                        credits = profileData.credits
                    )
                }
            }
        }
        item {
            Spacer(Modifier.height(10.dp))
        }
        item {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "ACTIVE DEPLOYMENT",
                    color = colorResource(R.color.white),
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(start = 20.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.width(50.dp))
                Text(
                    text = "AIR COURIER LIVE",
                    color = Color(0xFFAEC3F1),
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(top = 5.dp, end = 20.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        item {
            Spacer(Modifier.height(10.dp))
        }
        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .background(
                        color = colorResource(R.color.Secondary),
                        shape = RoundedCornerShape(10.dp)
                    ),
            ) {
                Column {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .background(
                                    colorResource(R.color.Secondary), RoundedCornerShape(10.dp),
                                )
                                .padding(all = 10.dp)
                        ) {
                            AsyncImage(
                                model = profileData.deploymentImage.ifEmpty { R.drawable.jacket },
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            )
                        }
                        Column(modifier = Modifier.weight(1f).padding(start = 10.dp)) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    Modifier.background(color = colorResource(R.color.Tertiary).copy(0.5F), shape = RoundedCornerShape(10.dp)),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "DHL EXPRESS",
                                        color = colorResource(R.color.white).copy(0.6f),
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily.Monospace,
                                        modifier = Modifier.padding(all = 5.dp),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                Spacer(Modifier.width(10.dp))
                                Text(
                                    text = "ETA TODAY 18:00",
                                    color = colorResource(R.color.Primary),
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.Monospace,
                                    modifier = Modifier.padding(bottom = 5.dp, top = 5.dp),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Spacer(Modifier.height(5.dp))
                            Row(
                                Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = profileData.deploymentTitle,
                                    color = colorResource(R.color.white),
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Spacer(Modifier.height(5.dp))
                            Row(
                                Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "OBSIDIAN . 40R",
                                    color = colorResource(R.color.white).copy(0.5f),
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Normal,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Spacer(Modifier.height(5.dp))
                            Row(
                                Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "TRACKING:",
                                    color = colorResource(R.color.white).copy(0.5f),
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Normal,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(Modifier.width(5.dp))
                                Text(
                                    text = profileData.trackingNumber,
                                    color = colorResource(R.color.white),
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .background(
                                color = colorResource(R.color.Neutral),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = colorResource(R.color.Tertiary),
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 20.dp, top = 5.dp, bottom = 5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                Modifier.padding(all = 10.dp)
                            ) {
                                Box(
                                    Modifier
                                        .background(
                                            color = colorResource(R.color.Primary),
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .size(10.dp)
                                        .align(Alignment.CenterVertically)
                                )
                                Spacer(Modifier.width(5.dp))
                                Text(
                                    "CONFIRMED",
                                    color = colorResource(R.color.white),
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Text(
                                "OUT FOR DELIVERY",
                                color = colorResource(R.color.white).copy(0.5f),
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                                .background(
                                    color = colorResource(R.color.Secondary),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .height(10.dp),
                        ) {
                            Canvas(
                                Modifier
                                    .height(10.dp)
                                    .fillMaxWidth(load)
                                    .background(
                                        color = colorResource(R.color.Primary),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                            ) {}
                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "BERLIN HUB",
                                color = colorResource(R.color.white).copy(0.5f),
                                fontSize = 12.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Normal,
                                maxLines = 1
                            )
                            Text(
                                "FLIGHT FG-420",
                                color = colorResource(R.color.white).copy(0.5f),
                                fontSize = 12.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Normal,
                                maxLines = 1
                            )
                            Text(
                                "MUNICH STN",
                                color = colorResource(R.color.white).copy(0.5f),
                                fontSize = 12.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Normal,
                                maxLines = 1
                            )
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(all = 10.dp)
                            .background(
                                color = colorResource(R.color.Primary),
                                shape = RoundedCornerShape(5.dp)
                            )
                            .clickable(
                                onClick = {
                                    // Handle back button click
                                },
                            )
                    ){
                        Text(
                            "TRACK DISPATCH PACKAGE",
                            color = colorResource(R.color.white),
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(all = 10.dp)
                                .align(Alignment.Center),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = colorResource(R.color.white),
                            modifier = Modifier
                                .size(45.dp)
                                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
                                .align(Alignment.CenterEnd)
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
        item {
            Spacer(Modifier.height(10.dp))
        }
        item { 
            Text(
                text = "/// ACCOUNT & SECURITY",
                color = colorResource(R.color.white),
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
            )
        }
        item {
            Spacer(Modifier.height(10.dp))
        }
        item {
            account()
        }
        item {
            Spacer(Modifier.height(10.dp))
        }
        item {
            Text(
                text = "/// SYSTEM SERVICES",
                color = colorResource(R.color.white),
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
            )
        }
        item {
            Spacer(Modifier.height(10.dp))
        }
        item {
            services()
        }
        item {
            Spacer(Modifier.height(30.dp))
        }
        item {
            end()
        }
        item {
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
fun MemberProfileCard(
    modifier: Modifier = Modifier,
    name: String,
    role: String,
    memberId: String,
    location: String,
    tier: String,
    orders: String,
    savedUnits: String,
    credits: String,
) {
    val background = Color(0xFF171717)
    val border = Color(0xFF292929)
    val secondaryText = Color(0xFF9B9B9B)
    val primaryText = Color(0xFFF4F4F4)
    val blue = Color(0xFF1559FF)

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = background
        ),
        border = BorderStroke(1.dp, border)
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxWidth()
        ) {
            val isCompact = maxWidth < 600.dp
            val horizontalPadding = if (isCompact) 18.dp else 32.dp
            val verticalPadding = if (isCompact) 20.dp else 32.dp

            Canvas(
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(22.dp))
            ) {
                val centerX = size.width * 0.88f
                val centerY = size.height * 0.20f
                val circleRadius = if (size.width < 600.dp.toPx()) 90.dp.toPx() else 125.dp.toPx()

                drawCircle(
                    color = Color(0xFF242424),
                    radius = circleRadius,
                    center = Offset(centerX, centerY),
                    style = Stroke(width = 3.dp.toPx())
                )

                for (i in 0..2) {
                    val gridWidth = if (size.width < 600.dp.toPx()) 45.dp.toPx() else 65.dp.toPx()
                    val x = centerX - gridWidth + i * gridWidth
                    drawLine(
                        color = Color(0xFF242424),
                        start = Offset(x, centerY - circleRadius * 0.6f),
                        end = Offset(x, centerY + circleRadius * 0.6f),
                        strokeWidth = 3.dp.toPx()
                    )
                }

                for (i in 0..2) {
                    val gridHeight = if (size.width < 600.dp.toPx()) 45.dp.toPx() else 75.dp.toPx()
                    val y = centerY - gridHeight + i * gridHeight
                    drawLine(
                        color = Color(0xFF242424),
                        start = Offset(centerX - circleRadius * 0.55f, y),
                        end = Offset(centerX + circleRadius * 0.55f, y),
                        strokeWidth = 3.dp.toPx()
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding, vertical = verticalPadding)
            ) {
                if (isCompact) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Top
                        ) {
                            ProfileAvatar(
                                size = 78.dp,
                                textSize = 26.sp,
                                blue = blue,
                                primaryText = primaryText
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = name,
                                        color = primaryText,
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    TierBadge(text = tier, compact = true)
                                }

                                Spacer(modifier = Modifier.height(5.dp))

                                Text(
                                    text = role,
                                    color = secondaryText,
                                    fontSize = 11.sp,
                                    letterSpacing = 1.1.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = memberId,
                                        color = Color(0xFF8BAEFF),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(text = "  •  ", color = secondaryText, fontSize = 12.sp)
                                    Text(
                                        text = location,
                                        color = secondaryText,
                                        fontSize = 12.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        ProfileAvatar(
                            size = 110.dp,
                            textSize = 36.sp,
                            blue = blue,
                            primaryText = primaryText
                        )

                        Spacer(modifier = Modifier.width(28.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = name,
                                    color = primaryText,
                                    fontSize = 36.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.width(20.dp))
                                TierBadge(text = tier)
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = role,
                                color = secondaryText,
                                fontSize = 16.sp,
                                letterSpacing = 1.5.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Spacer(modifier = Modifier.height(14.dp))

                            Row {
                                Text(
                                    text = memberId,
                                    color = Color(0xFF8BAEFF),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = "  •  ", color = secondaryText, fontSize = 16.sp)
                                Text(text = location, color = secondaryText, fontSize = 16.sp)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(if (isCompact) 20.dp else 32.dp))
                HorizontalDivider(color = Color(0xFF252525), thickness = 1.dp)
                Spacer(modifier = Modifier.height(if (isCompact) 16.dp else 22.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(if (isCompact) 8.dp else 16.dp)
                ) {
                    StatisticCard(
                        modifier = Modifier.weight(1f),
                        value = orders,
                        label = "ORDERS\nPLACED",
                        valueColor = primaryText,
                        compact = isCompact
                    )
                    StatisticCard(
                        modifier = Modifier.weight(1f),
                        value = savedUnits,
                        label = "SAVED UNITS",
                        valueColor = primaryText,
                        compact = isCompact
                    )
                    StatisticCard(
                        modifier = Modifier.weight(1f),
                        value = credits,
                        label = "STORE\nCREDITS",
                        valueColor = blue,
                        compact = isCompact
                    )
                }
            }
        }
    }
}

@Composable
private fun TierBadge(text: String, compact: Boolean = false) {
    Box(
        modifier = Modifier
            .border(width = 1.dp, color = Color(0xFF1559FF), shape = RoundedCornerShape(3.dp))
            .background(Color(0xFF151C31))
            .padding(
                horizontal = if (compact) 8.dp else 14.dp,
                vertical = if (compact) 5.dp else 7.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color(0xFF6D96FF),
            fontSize = if (compact) 9.sp else 13.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = if (compact) 1.sp else 1.5.sp
        )
    }
}

@Composable
private fun StatisticCard(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    valueColor: Color,
    compact: Boolean = false
) {
    Column(
        modifier = modifier
            .height(if (compact) 95.dp else 145.dp)
            .border(width = 1.dp, color = Color(0xFF292929), shape = RoundedCornerShape(if (compact) 12.dp else 16.dp))
            .padding(
                horizontal = if (compact) 10.dp else 20.dp,
                vertical = if (compact) 10.dp else 18.dp
            )
    ) {
        Text(
            text = value,
            color = valueColor,
            fontSize = if (compact) 23.sp else 34.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = label,
            color = Color(0xFF9B9B9B),
            fontSize = if (compact) 12.sp else 14.sp,
            lineHeight = if (compact) 18.sp else 24.sp,
            letterSpacing = 1.5.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
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
                color = if (isSelected) colorResource(R.color.Primary) else colorResource(R.color.Secondary),
                shape = RoundedCornerShape(5.dp)
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
private fun ProfileAvatar(
    size: Dp,
    textSize: TextUnit,
    blue: Color,
    primaryText: Color
) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(size * 0.18f))
            .background(Color(0xFF353535)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "MV",
            color = primaryText,
            fontSize = textSize,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(if (size < 90.dp) 5.dp else 7.dp)
                .size(if (size < 90.dp) 14.dp else 18.dp)
                .clip(CircleShape)
                .background(Color(0xFF111111))
                .border(width = 2.dp, color = blue, shape = CircleShape)
        )
    }
}

@Preview
@Composable
fun ProfilePreview(){
    ProfilScreen()
}

@Composable
fun account() {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .background(
                color = colorResource(R.color.Secondary),
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(10.dp),
                color = colorResource(R.color.Tertiary)
            ),
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().clickable(onClick = {}),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    Modifier
                        .padding(top = 10.dp, bottom = 10.dp, start = 10.dp)
                        .background(
                            color = colorResource(R.color.Tertiary).copy(0.5f),
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(R.drawable.outline_delivery_truck_speed_24),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(45.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .padding(all = 10.dp)
                    )
                }
                Spacer(Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "KREUZBERG ST...",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.white),
                            fontFamily = FontFamily.Monospace,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            "DEFAULT",
                            fontSize = 7.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.white),
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier
                                .background(
                                    color = colorResource(R.color.Primary),
                                    shape = RoundedCornerShape(7.dp)
                                )
                                .padding(start = 5.dp, end = 5.dp, top = 2.5.dp, bottom = 2.5.dp)
                        )
                    }
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = "GORLITZER STR. 44,10997",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.white).copy(0.5f),
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(Modifier.width(10.dp))

                Row(
                    modifier = Modifier.padding(end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "DOCK 04",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.white).copy(0.5f),
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = colorResource(R.color.white).copy(0.5f),
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Spacer(Modifier.height(10.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = colorResource(R.color.Tertiary))
            )

            Row(
                modifier = Modifier.fillMaxWidth().clickable(onClick = {}),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    Modifier
                        .padding(top = 10.dp, bottom = 10.dp, start = 10.dp)
                        .background(
                            color = colorResource(R.color.Tertiary).copy(0.5f),
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(R.drawable.outline_fingerprint_24),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(45.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .padding(all = 10.dp)
                    )
                }
                Spacer(Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "HARDWARE\nAUTHENTICATION",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.white),
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = "FACE ID & HARDENDE PASSKEY",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.white).copy(0.5f),
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }

                Spacer(Modifier.width(10.dp))

                Row(
                    modifier = Modifier.padding(end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "SECURED",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF84A6F3),
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier
                            .background(
                                color = colorResource(R.color.Tertiary),
                                shape = RoundedCornerShape(7.dp)
                            )
                            .padding(start = 5.dp, end = 5.dp, top = 2.5.dp, bottom = 2.5.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = colorResource(R.color.white).copy(0.5f),
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Spacer(Modifier.height(10.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = colorResource(R.color.Tertiary))
            )

            Row(
                modifier = Modifier.fillMaxWidth().clickable(onClick = {}),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    Modifier
                        .padding(top = 10.dp, bottom = 10.dp, start = 10.dp)
                        .background(
                            color = colorResource(R.color.Tertiary).copy(0.5f),
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(R.drawable.baseline_payment_24),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(45.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .padding(all = 10.dp)
                    )
                }
                Spacer(Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "PAYMENT METHODS",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.white),
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = "BLACK CARD.... 9104(APPLE PAY)",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.white).copy(0.5f),
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(Modifier.width(10.dp))

                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = colorResource(R.color.white).copy(0.5f),
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(30.dp)
                )
            }

            Spacer(Modifier.height(10.dp))
        }
    }
}

@Composable
fun services(){
    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .background(
                color = colorResource(R.color.Secondary),
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(10.dp),
                color = colorResource(R.color.Tertiary)
            ),
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().clickable(onClick = {}),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    Modifier
                        .padding(top = 10.dp, bottom = 10.dp, start = 10.dp)
                        .background(
                            color = colorResource(R.color.Tertiary).copy(0.5f),
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = null,
                        tint = colorResource(R.color.white),
                        modifier = Modifier
                            .size(45.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .padding(all = 10.dp)
                    )
                }
                Spacer(Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "FIELD REPAIR ARCHIVE",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.white),
                            fontFamily = FontFamily.Monospace,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = "LIFETIME HARDWARE WARRANTY ",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.white).copy(0.5f),
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(Modifier.width(10.dp))

                Row(
                    modifier = Modifier.padding(end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "0 CLAIMS",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff89bdff),
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = colorResource(R.color.white).copy(0.5f),
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Spacer(Modifier.height(10.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = colorResource(R.color.Tertiary))
            )

            Row(
                modifier = Modifier.fillMaxWidth().clickable(onClick = {}),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    Modifier
                        .padding(top = 10.dp, bottom = 10.dp, start = 10.dp)
                        .background(
                            color = colorResource(R.color.Tertiary).copy(0.5f),
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(R.drawable.outline_notifications_active_24),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(45.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .padding(all = 10.dp)
                    )
                }
                Spacer(Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "RESTOCK & DROP ALERTS",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.white),
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier.padding(top = 10.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = "PROTOTYPE RELEASES & VAULT",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.white).copy(0.5f),
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }

                Spacer(Modifier.width(5.dp))

                Row(
                    modifier = Modifier.padding(end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "ENABLED",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(R.color.white).copy(0.5f),
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 2.5.dp, bottom = 2.5.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = colorResource(R.color.white).copy(0.5f),
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Spacer(Modifier.height(10.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = colorResource(R.color.Tertiary))
            )

            Row(
                modifier = Modifier.fillMaxWidth().clickable(onClick = {}),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    Modifier
                        .padding(top = 10.dp, bottom = 10.dp, start = 10.dp)
                        .background(
                            color = colorResource(R.color.Tertiary).copy(0.5f),
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(R.drawable.outline_headset_mic_24),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(45.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .padding(all = 10.dp)
                    )
                }
                Spacer(Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "CONCIERGE & SUPPORT",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.white),
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = "DIRECT FIELD DISPATCH ASSIST",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.white).copy(0.5f),
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(Modifier.width(10.dp))

                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = colorResource(R.color.white).copy(0.5f),
                    modifier = Modifier.padding(end = 10.dp).size(30.dp)
                )
            }

            Spacer(Modifier.height(10.dp))
        }
    }
}

@Composable
fun end(){
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 10.dp)
                .background(color = colorResource(R.color.Secondary), shape = RoundedCornerShape(10.dp))
                .border(width = 1.dp, color = colorResource(R.color.Tertiary), shape = RoundedCornerShape(10.dp))
                .clickable(onClick = {}),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = null,
                tint = Color(0xffff6969),
                modifier = Modifier.size(30.dp)
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = "DISCONNECT SESSION (LOGOUT) ",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xffff6969),
                fontFamily = FontFamily.SansSerif
            )
        }
        Spacer(Modifier.height(20.dp))
        Text(
            text = "FORGE OS v4.2 // ARCHITECTURAL KERNEL",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White.copy(0.6f),
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(5.dp))
        Text(
            text = "SECURITY PROTOCOL: AES-256-GCM // OPERATOR ONLINE",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White.copy(0.4f),
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
