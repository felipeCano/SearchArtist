package com.search.artist.data.model.artistDetail

data class ArtistDetailResponse(
    val aliases: List<Aliase>,
    val data_quality: String,
    val id: Int,
    val images: List<Image>,
    val members: List<Member>,
    val name: String,
    val namevariations: List<String>,
    val profile: String,
    val releases_url: String,
    val resource_url: String,
    val uri: String,
    val urls: List<String>
)