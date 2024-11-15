package lastlab.uvg.edu.gt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import lastlab.uvg.edu.gt.presentacion.navigation.MainNavHost
import lastlab.uvg.edu.gt.ui.theme.LastLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LastLabTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { paddingValues ->
                    MainNavHost(
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}

