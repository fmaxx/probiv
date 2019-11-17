package ru.otus.probiv.api

import retrofit2.Response
import java.io.IOException

/**
 * Created by Maxim Firsov on 2019-11-17.
 * PowerDot
 * firsoffmaxim@gmail.com
 */
open class BaseRepository {
    protected suspend fun <T : Any> make(call: suspend () -> Response<T>, error: String): RepositoryResult<T> {
        val response = call.invoke();
        return if (response.isSuccessful) {
            RepositoryResult.Success(response.body()!!)
        } else {
            RepositoryResult.Error(IOException(response.errorBody().toString()))
        }
    }
}