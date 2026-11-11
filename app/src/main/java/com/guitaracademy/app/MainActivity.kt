package com.guitaracademy.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.guitaracademy.app.ui.GuitarAcademyApp
import com.guitaracademy.app.ui.theme.GuitarAcademyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuitarAcademyTheme {
                GuitarAcademyApp()
            }
        }
    }
}
