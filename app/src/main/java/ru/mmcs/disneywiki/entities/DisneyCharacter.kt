package ru.mmcs.disneywiki.entities

data class DisneyCharacter(
    val _id: Int,
    val films: List<String>,
    val shortFilms: List<String>,
    val tvShows: List<String>,
    val videoGames: List<String>,
    val allies: List<String>,
    val enemies: List<String>,
    val name: String,
    val imageUrl: String,
)
