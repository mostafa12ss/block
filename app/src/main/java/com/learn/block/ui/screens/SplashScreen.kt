package com.learn.block.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.learn.block.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigation: (String) -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000) // 2 seconds delay
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            onNavigation("MAIN")
        } else {
            onNavigation("WELCOME")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF080808)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.forgedark),
            contentDescription = "Logo",
            modifier = Modifier.size(120.dp),
            contentScale = ContentScale.Fit
        )
    }
}
