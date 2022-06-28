package com.nextory.testapp.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nextory.testapp.ui.theme.TestAppTheme
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun TestApp() {
    TestAppTheme {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()

        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
        }

        DestinationsNavHost(navGraph = NavGraphs.root)
    }
}