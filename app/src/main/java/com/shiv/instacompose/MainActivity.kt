package com.shiv.instacompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.shiv.instacompose.ui.screen.profile.UserProfileScreen
import com.shiv.instacompose.ui.theme.InstaComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InstaComposeTheme {
                UserProfileScreen()
            }
        }
    }
}