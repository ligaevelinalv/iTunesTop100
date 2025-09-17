package com.example.itunestoptask.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.itunestoptask.presentation.navigation.AlbumNavHost
import com.example.itunestoptask.theme.darkOlive
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.light(darkOlive.hashCode(), darkOlive.hashCode())
            )
            val navController = rememberNavController()

            AlbumNavHost(
                navController = navController
            )
        }
    }
}
