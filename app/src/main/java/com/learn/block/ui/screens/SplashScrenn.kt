package com.learn.block.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// 1. استخدام ملف الـ R الخاص بمشروعك بشكل صحيح
import com.learn.block.R

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // 1. الزوايا الهندسية في أعلى الشاشة (متجهة للداخل)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp, top = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // الزاوية الشمال (بتبص للداخل)
            Column(horizontalAlignment = Alignment.Start) {
                Box(
                    modifier = Modifier
                        .size(30.dp, 2.dp)
                        .background(Color.Gray.copy(0.4f))
                )
                Box(
                    modifier = Modifier
                        .size(2.dp, 30.dp)
                        .background(Color.Gray.copy(0.4f))
                )
            }

            // الزاوية اليمين (بتبص للداخل)
            Column(horizontalAlignment = Alignment.End) {
                Box(
                    modifier = Modifier
                        .size(30.dp, 2.dp)
                        .background(Color.Gray.copy(0.4f))
                )
                Box(
                    modifier = Modifier
                        .size(2.dp, 30.dp)
                        .background(Color.Gray.copy(0.4f))
                )
            }
        }

        // 2. اللوجو المربع في منتصف الشاشة بالظبط
        Box(
            modifier = Modifier
                .size(240.dp)
                .align(Alignment.Center)
                .shadow(
                    elevation = 30.dp,
                    shape = RoundedCornerShape(4.dp),
                    spotColor = Color(0xFF007AFF),
                    ambientColor = Color(0xFF007AFF)
                )
                .background(Color.White, shape = RoundedCornerShape(4.dp))
                .border(
                    width = 2.dp,
                    color = Color(0xFF007AFF).copy(alpha = 0.4f),
                    shape = RoundedCornerShape(4.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            // 2. كتابة المورد بشكل كامل R.drawable.forge
            Image(
                painter = painterResource(id = R.drawable.forge),
                contentDescription = "Forge Logo",
                modifier = Modifier.fillMaxSize(0.85f)
            )
        }

        // 3. النص والشكل الهندسي في الأسفل
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CornerLineDecoration(isLeft = true)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "ENGINEERED PRECISION",
                color = Color(0xFFCCCCCC),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 14.dp)
            )
Spacer(
    modifier = Modifier.width(16.dp)
)
            CornerLineDecoration(isLeft = false)
        }
    }
}

@Composable
private fun CornerLineDecoration(isLeft: Boolean) {
    val grayColor = Color(0xFF555555)
    val whiteColor = Color.White.copy(0.8f)

    Canvas(
        modifier = Modifier
            .width(32.dp)
            .height(20.dp) // طول الخط العمودي 20dp
    ) {
        val strokePx = 1.2.dp.toPx()
        val w = size.width
        val h = size.height
        val midX = w / 2f
        val bottomLineLength = midX + 4.dp.toPx()

        // 1. الخط الأفقي العلوي (أبيض ومحاذي لنص الكلام)
        drawLine(
            color = whiteColor,
            start = androidx.compose.ui.geometry.Offset(0f, 0f),
            end = androidx.compose.ui.geometry.Offset(w, 0f),
            strokeWidth = strokePx
        )

        // 2. الخط العمودي (طوله 20dp بالظبط واصل للأسفل)
        drawLine(
            color = grayColor,
            start = androidx.compose.ui.geometry.Offset(midX, 0f),
            end = androidx.compose.ui.geometry.Offset(midX, h),
            strokeWidth = strokePx
        )

        // 3. الخط الأفقي السفلي (رمادي)
        val endX = if (isLeft) midX + bottomLineLength else midX - bottomLineLength
        drawLine(
            color = grayColor,
            start = androidx.compose.ui.geometry.Offset(midX, h),
            end = androidx.compose.ui.geometry.Offset(endX, h),
            strokeWidth = strokePx
        )
    }
}
@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}