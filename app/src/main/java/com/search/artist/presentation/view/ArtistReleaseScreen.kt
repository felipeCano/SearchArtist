package com.search.artist.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.search.artist.data.model.artistiReleases.Release
import com.search.artist.presentation.viewmodel.ArtistReleaseViewModel

@Composable
fun ArtistReleaseScreen(
    artistId: Int,
    modifier: Modifier = Modifier,
    viewModel: ArtistReleaseViewModel = hiltViewModel()
) {
    val albums = viewModel.getAlbumsPagingFlow(artistId).collectAsLazyPagingItems()

    LazyColumn(modifier = modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 16.dp)) {
        items(
            count = albums.itemCount,
            key = { index -> albums.peek(index)?.id ?: index }
        ) { index ->
            val album = albums[index]
            album?.let { AlbumItem(it) }
        }

        when (albums.loadState.refresh) {
            is LoadState.Loading -> item { CircularProgressIndicator() }
            is LoadState.Error -> item { Text("Error cargando álbumes") }
            else -> {}
        }
    }
}

@Composable
fun AlbumItem(album: Release) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = album.thumb,
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )

            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = album.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${album.year} | ${album.label}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
