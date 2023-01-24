package com.dev.myHwasil

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.dev.myHwasil.navigation.SetupNavGraph
import com.dev.myHwasil.ui.theme.MyHwasilTheme
import com.kakao.util.maps.helper.Utility
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Base64

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyHwasilTheme {
                val navController = rememberNavController();
                SetupNavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyHwasilTheme {
        Greeting("Android")
    }
}