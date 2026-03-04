package com.search.artist.data.model.artistiReleases

data class Pagination(
    val items: Int,
    val page: Int,
    val pages: Int,
    val per_page: Int,
    val urls: Urls
)