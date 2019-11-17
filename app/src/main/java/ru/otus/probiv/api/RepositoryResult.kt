package ru.otus.probiv.api
sealed class RepositoryResult<out T: Any>  {
    data class Success<out T : Any>(val value : T) : RepositoryResult<T>()
    data class Error(val error: Throwable)  : RepositoryResult<Nothing>()
}