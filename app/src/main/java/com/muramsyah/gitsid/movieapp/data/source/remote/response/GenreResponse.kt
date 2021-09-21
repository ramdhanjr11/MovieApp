package com.muramsyah.gitsid.movieapp.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreResponse(
    @field:SerializedName("genres")
    val genres: List<Genres?>? = null
): Parcelable

@Parcelize
data class Genres(
    @field:SerializedName("id")
    val id: Int? = null,
    val name: String? = null
): Parcelable

