package com.dev.myHwasil.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dev.myHwasil.R
import com.dev.myHwasil.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(key1 = true)
    {
        delay(3000)
        navController.navigate(Screen.Home.route)
    }
    Splash()
}


@Composable
fun Splash() {
    Box(
        modifier = Modifier
            .background(Color(53, 127, 187))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val logoImage = painterResource(id = R.drawable.logo);
        Image(painter = logoImage, contentDescription = null)

    }
}



