package com.search.artist.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.search.artist.data.util.Resource
import com.search.artist.presentation.viewmodel.SearchArtistViewModel
import com.search.artist.data.model.searchArtist.Result

@Composable
fun SearchArtistScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchArtistViewModel = hiltViewModel()
){
    val state by viewModel.searchArtist.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.searchArtist("nevermind","nirvana",1,3)
    }

    Box(modifier = modifier.fillMaxSize()) {
        when (state) {
            is Resource.Loading<*> -> CircularProgressIndicator(modifier.align(Alignment.Center))
            is Resource.Success<*> -> {
                ArtistList(state.data?.results ?: emptyList())
            }
            is Resource.Error<*> -> {
                Text(
                    text = "Error: ${state.message}",
                    color = Color.Red,
                    modifier = modifier.align(Alignment.Center)
                )
            }
            is Resource.Idle<*> ->{}
        }
    }
}

@Composable
fun ArtistList(artists: List<Result>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(artists) { artist ->
            ArtistItem(artist)
        }
    }
}

@Composable
fun ArtistItem(artist: Result) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = artist.thumb,
                contentDescription = null,
                modifier = Modifier.size(60.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = artist.title ?: "Sin nombre",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}