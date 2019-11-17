package ru.otus.probiv.api.provider.fss

import ru.otus.probiv.api.BaseRepository
import ru.otus.probiv.api.RepositoryResult
import ru.otus.probiv.api.provider.fss.data.FssResponse
import ru.otus.probiv.api.provider.fss.data.FssStartSearchRequest
import ru.otus.probiv.api.provider.fss.data.FssStatusResponse
import java.io.IOException

/**
 * Created by Maxim Firsov on 2019-11-17.
 * PowerDot
 * firsoffmaxim@gmail.com
 */
class FssRepository : BaseRepository() {

    private val api: FssApi by lazy { FssApiService.instance }

    suspend fun startSearchPhysical(input: FssStartSearchRequest): RepositoryResult<FssResponse> {
        val response = api.startSearchPhysical(
            input.region,
            input.firstName,
            input.lastName,
            input.secondName,
            input.birthDate
        )

        return if (response.isSuccessful) {
            RepositoryResult.Success(response.body()!!)
        } else {
            RepositoryResult.Error(IOException(response.errorBody()?.toString()))
        }
    }

    suspend fun getTaskStatus(task: String): RepositoryResult<FssStatusResponse> {
        val response = api.getTaskStatus(task)
        return if (response.isSuccessful) {
            RepositoryResult.Success(response.body()!!)
        } else {
            RepositoryResult.Error(IOException(response.errorBody()?.toString()))
        }
    }
}

