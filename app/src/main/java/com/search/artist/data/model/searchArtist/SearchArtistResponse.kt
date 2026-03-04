package com.search.artist.data.model.searchArtist

data class SearchArtistResponse(
    val pagination: Pagination,
    val results: List<Result>
)