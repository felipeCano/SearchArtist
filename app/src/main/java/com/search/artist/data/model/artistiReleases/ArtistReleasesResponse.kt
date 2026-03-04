package com.search.artist.data.model.artistiReleases

data class ArtistReleasesResponse(
    val pagination: Pagination,
    val releases: List<Release>
)