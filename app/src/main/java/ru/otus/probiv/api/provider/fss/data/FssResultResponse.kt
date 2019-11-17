package ru.otus.probiv.api.provider.fss.data

data class FssResultResponse(
    val result: List<Item>,
    val status: Int,
    val task_end: String,
    val task_start: String
)

data class Item(
    val query: Query,
    val result: List<SubjectItem>,
    val status: Int
)

data class Query(
    val params: Params,
    val type: Int
)

data class SubjectItem(
    val bailiff: String,
    val department: String,
    val details: String,
    val exe_production: String,
    val ip_end: String,
    val name: String,
    val subject: String
)

data class Params(
    val number: String
)