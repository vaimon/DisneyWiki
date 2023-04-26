package ru.mmcs.disneywiki.entities

data class DisneyQueryResult(
    val info: QueryInfo,
    val data: List<DisneyCharacter?>?
)