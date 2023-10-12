package com.francomucci.storitest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.francomucci.storitest.ui.navigation.Navigation
import com.francomucci.storitest.ui.uikit.theme.StoriTestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StoriTestAppTheme {
                Scaffold { padding ->
                    Navigation(Modifier.padding(padding))
                }
            }
        }
    }
}