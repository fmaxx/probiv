package ru.otus.probiv.api.provider.fss.data

/**
 * Created by Maxim Firsov on 2019-11-17.
 * PowerDot
 * firsoffmaxim@gmail.com
 */
data class FssResponse(
    val status: String,
    val code: Int,
    val exception: String?,
    val response: Task?
)

data class Task(
    val task: String
)