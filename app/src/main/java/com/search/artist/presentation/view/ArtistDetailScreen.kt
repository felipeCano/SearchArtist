package com.search.artist.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.search.artist.data.model.artistDetail.ArtistDetailResponse
import com.search.artist.data.util.Resource
import com.search.artist.presentation.viewmodel.ArtistDetailViewModel

@Composable
fun ArtistDetailScreen(
    onNavigateToAlbums : (String) -> Unit,
    modifier: Modifier = Modifier,
    artistId: Int?,
    viewModel: ArtistDetailViewModel = hiltViewModel()
){
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(artistId) {
        viewModel.loadArtistDetail(artistId!!)
    }

    when (val resource = state) {
        is Resource.Loading -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is Resource.Success -> {
            resource.data?.let { artist ->
                ArtistDetailContent(
                    modifier,
                    artist = artist,
                    onNavigateToAlbums = { artistId ->
                        onNavigateToAlbums(artistId)
                    }
                )
            }
        }
        is Resource.Error -> {
            InfoMessageCard("No artist information found")
        }
        is Resource.Idle -> {
        }
    }
}

@Composable
fun ArtistDetailContent(
    modifier: Modifier = Modifier,
    artist: ArtistDetailResponse,
    onNavigateToAlbums: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = artist.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "About the artist", style = MaterialTheme.typography.titleLarge)
            Text(
                text = artist.profile.ifBlank { "No information is available." },
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )

            if (!artist.members.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Band members", style = MaterialTheme.typography.titleLarge)
                FlowRow(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    artist.members.forEach { member ->
                        AssistChip(
                            onClick = { },
                            label = { Text(member.name) },
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onNavigateToAlbums(artist.id.toString()) },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("View Artist Albums")
        }
    }
}

@Composable
fun InfoMessageCard(message: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(24.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}