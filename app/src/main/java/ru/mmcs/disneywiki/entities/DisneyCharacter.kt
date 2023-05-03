package ru.mmcs.disneywiki.entities

import android.os.Parcel
import android.os.Parcelable

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
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.createStringArrayList()!!.toList(),
        parcel.createStringArrayList()!!.toList(),
        parcel.createStringArrayList()!!.toList(),
        parcel.createStringArrayList()!!.toList(),
        parcel.createStringArrayList()!!.toList(),
        parcel.createStringArrayList()!!.toList(),
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(_id)
        parcel.writeStringList(films)
        parcel.writeStringList(shortFilms)
        parcel.writeStringList(tvShows)
        parcel.writeStringList(videoGames)
        parcel.writeStringList(allies)
        parcel.writeStringList(enemies)
        parcel.writeString(name)
        parcel.writeString(imageUrl)
        films.joinToString(", ")
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DisneyCharacter> {
        override fun createFromParcel(parcel: Parcel): DisneyCharacter {
            return DisneyCharacter(parcel)
        }

        override fun newArray(size: Int): Array<DisneyCharacter?> {
            return arrayOfNulls(size)
        }
    }

    enum class ActingType(public val description: String) {
        Film("Фильм"),
        ShortFilm("Короткометражка"),
        TvShow("Сериал"),
        Game("Игра")
    }
}
