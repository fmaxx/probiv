package ru.otus.probiv.api.provider.fss.data

data class FssStatusResponse(val status: String,
                             val code: Int,
                             val exception: String?,
                             val response: TaskProgress?)

data class TaskProgress(
    val status: String,
    val progress: String
)