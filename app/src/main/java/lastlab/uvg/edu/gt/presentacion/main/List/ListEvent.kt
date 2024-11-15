package lastlab.uvg.edu.gt.presentacion.main.List

sealed interface ListEvent{
    data object ForceError: ListEvent
    data object RetryClick: ListEvent
}