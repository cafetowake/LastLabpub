package lastlab.uvg.edu.gt.presentacion.main.Profile

sealed interface ProfileEvent{
    data object ForceError: ProfileEvent
    data object RetryClick: ProfileEvent
}