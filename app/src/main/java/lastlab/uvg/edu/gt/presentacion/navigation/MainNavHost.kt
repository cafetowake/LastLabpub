package lastlab.uvg.edu.gt.presentacion.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import lastlab.uvg.edu.gt.presentacion.main.List.ListDestination
import lastlab.uvg.edu.gt.presentacion.main.List.list
import lastlab.uvg.edu.gt.presentacion.main.Profile.navigateToProfile
import lastlab.uvg.edu.gt.presentacion.main.Profile.profile  // Asegúrate de que esté importado

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = ListDestination,
        modifier = modifier
    ) {
        list(navigateToProfile = { coinId -> navController.navigateToProfile(coinId.toString()) })
        profile(onBackClick = { navController.navigateUp() })
    }
}
