package com.learn.block.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.block.R

// بيانات كل عنصر في الشريط
data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val badgeCount: Int? = null
)

@Composable
fun BottomBar(
    selectedRoute: String = "HOME",
    onItemSelected: (String) -> Unit = {}
) {
    val items = listOf(
        NavigationItem("HOME", Icons.Outlined.Home),
        NavigationItem("SHOP", Icons.Outlined.ShoppingCart),
        NavigationItem("SAVED", Icons.Outlined.Favorite),
        NavigationItem("PROFILE", Icons.Outlined.AccountCircle)
    )

    val activeColor =colorResource(R.color.Primary)
    val inactiveColor = Color.White.copy(alpha = 0.6f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                color = colorResource(R.color.Secondary),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
            .padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val isSelected = item.title == selectedRoute
                val contentColor = if (isSelected) activeColor else inactiveColor

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .clickable { onItemSelected(item.title) }
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    // أيقونة مع دعم الـ Badge للإشعارات
                    BadgedBox(
                        badge = {
                            if (item.badgeCount != null) {
                                Box(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .background(activeColor, shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = item.badgeCount.toString(),
                                        color = Color.White,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = contentColor,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // نص العنصر
                    Text(
                        text = item.title,
                        color = contentColor,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // الخط الأزرق السفلي للعنصر المحدد فقط
                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .width(30.dp)
                                .height(3.dp)
                                .background(activeColor, shape = RoundedCornerShape(2.dp))
                        )
                    } else {
                        Spacer(modifier = Modifier.height(3.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    var selectedTab by remember { mutableStateOf("HOME") }
    BottomBar(
        selectedRoute = selectedTab,
        onItemSelected = { selectedTab = it }
    )
}