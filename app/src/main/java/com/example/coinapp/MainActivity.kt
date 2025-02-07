package com.example.coinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.coinapp.core.di.mainModule
import com.example.coinapp.core.navigation.MainNavGraph
import com.example.coinapp.core.theme.AppTheme
import com.example.coinapp.feature.list_page.di.assetsListModule
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinApplication(application = {
                modules(
                    mainModule,
                    assetsListModule,
                )
            }) {
                val navController = rememberNavController()

                AppTheme {
                    Scaffold { innerPadding ->
                        MainNavGraph(
                            modifier = Modifier.padding(innerPadding),
                            navHostController = navController
                        )
                    }
                }
            }
        }
    }
}
