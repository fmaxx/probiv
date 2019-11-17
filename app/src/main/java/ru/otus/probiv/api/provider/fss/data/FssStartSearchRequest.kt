package ru.otus.probiv.api.provider.fss.data

data class FssStartSearchRequest(
    val region: Int,
    val firstName: String,
    val lastName: String,
    val birthDate: String?,
    val secondName: String?

)