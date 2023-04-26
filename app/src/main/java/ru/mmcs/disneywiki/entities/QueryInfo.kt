package ru.mmcs.disneywiki.entities

data class QueryInfo(
    val totalPages: Int,
    val nextPage: String?,
    val previousPage: String?
)