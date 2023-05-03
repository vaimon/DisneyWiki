package ru.mmcs.disneywiki.viemodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mmcs.disneywiki.adapters.CharacterActRvAdapter
import ru.mmcs.disneywiki.entities.DisneyCharacter

class CharacterActViewmodel(val character: DisneyCharacter) : ViewModel() {
    val characterActRvAdapter: CharacterActRvAdapter = CharacterActRvAdapter(getActingHistory())

    private fun getActingHistory() : List<Pair<DisneyCharacter.ActingType, String>>{
        val res = mutableListOf<Pair<DisneyCharacter.ActingType, String>>()
        res.addAll(character.films.map { DisneyCharacter.ActingType.Film to it })
        res.addAll(character.shortFilms.map { DisneyCharacter.ActingType.ShortFilm to it })
        res.addAll(character.tvShows.map { DisneyCharacter.ActingType.TvShow to it })
        res.addAll(character.videoGames.map { DisneyCharacter.ActingType.Game to it })
        return res
    }

    class Factory(private val character: DisneyCharacter) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CharacterActViewmodel(character) as T
        }
    }
}