package com.search.artist.data.model.artistiReleases

data class Release(
    val artist: String,
    val format: String,
    val id: Int,
    val label: String,
    val main_release: Int,
    val resource_url: String,
    val role: String,
    val stats: Stats,
    val status: String,
    val thumb: String,
    val title: String,
    val type: String,
    val year: Int
)