package lastlab.uvg.edu.gt.presentacion.main.Profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDestination(
    val id: String
)

fun NavController.navigateToProfile(
    coinId: String
){
    this.navigate(ProfileDestination(
        id=coinId
    ))
}
fun NavGraphBuilder.monsterProfile(
    onBackClick: () -> Unit
){
    composable<ProfileDestination>{
        ProfileRoute(onBackClick = onBackClick)
    }
}