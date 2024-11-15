package lastlab.uvg.edu.gt.presentacion.main.List

import kotlinx.serialization.Serializable

@Serializable
data object ListDestination

fun NavGraphBuilder.list(
    navigateToProfile: (String) -> Unit
)