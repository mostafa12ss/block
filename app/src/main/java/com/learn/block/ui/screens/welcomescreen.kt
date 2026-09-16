package com.learn.block.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.block.R
import com.learn.block.ui.theme.BlockTheme

@Composable
fun WelcomeScreen(
    onCreateAccount: () -> Unit = {},
    onLogin: () -> Unit = {},
    onGuest: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {


        Image(
            painter = painterResource(id = R.drawable.screenm),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.5f),
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 0.7f),
                            Color.Black.copy(alpha = 0.8f),
                            Color.Black.copy(alpha = 0.9f),
                            Color.Black
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 100.dp,
                    bottom = 40.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Text Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(80.dp)
                        .background(
                            color = colorResource(R.color.Primary)
                        )
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "ARCHITECTURAL\nPRECISION.\nUNCOMPROMISING UTILITY.",
                    color = Color.White.copy(alpha = 0.5f),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 17.sp
                )
            }

            Spacer(modifier = Modifier.weight(0.5f))

            Button(
                onClick = onCreateAccount,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(60.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.Primary)
                ),
                shape = RectangleShape
            ) {
                Text(
                    text = "CREATE ACCOUNT",
                    fontSize = 18.sp,
                    fontWeight = Bold,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif
                )
                Spacer(Modifier.width(15.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = onLogin,
                modifier = Modifier
                    .fillMaxWidth(0.9f).border(width = 1.dp, color = Color.Gray.copy(0.5f), shape = RectangleShape)
                    .height(60.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color.Black.copy(0.1f)
                ),
                shape = RectangleShape
            ) {
                Text(
                    text = "LOGIN",
                    fontSize = 18.sp,
                    fontWeight = Bold,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif
                )

            }
            Spacer(Modifier.height(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
Icon(
    imageVector = Icons.Default.Person,
    contentDescription = "personal",
    Modifier.size(15.dp),
    Color.White.copy(0.5f)
)
                Spacer(modifier = Modifier.width(10.dp))
Text(
    text="CONTINUE AS GUEST",
    fontSize = 15.sp,
    fontStyle = FontStyle.Normal,
    color = Color.White.copy(alpha = 0.5f),
    fontFamily = FontFamily.SansSerif,
    modifier = Modifier.clickable(
        onClick = onGuest
    )
)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    BlockTheme {
        WelcomeScreen()
    }
}
