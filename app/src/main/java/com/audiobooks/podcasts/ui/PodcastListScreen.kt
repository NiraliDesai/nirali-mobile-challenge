package com.audiobooks.podcasts.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.audiobooks.podcasts.model.Podcast

/**
 * Composable function to display the list of podcasts.
 * It fetches data from [PodcastListViewModel] and displays items using [LazyColumn].
 *
 * @param viewModel The ViewModel providing the list of podcasts.
 * @param onShowDetails Callback to navigate to the PodcastDetailsScreen when an item is clicked.
 */

@Composable
fun PodcastListScreen(
    viewModel: PodcastListViewModel = viewModel(),
    onShowDetails: (Podcast) -> Unit
) {
    // Collects the list of podcasts as a State to trigger recompositions
    val podcasts = viewModel.podcasts.collectAsState().value

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(
            text = "Podcasts",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // LazyColumn for efficient scrolling of podcast items
        LazyColumn {
            items(podcasts) { podcast ->
                Column {
                    PodcastItem(podcast, onShowDetails)
                    // Adds a horizontal divider between items
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                }
            }
        }
    }
}


/**
 * Composable function to display a single podcast item.
 *
 * @param podcast The podcast data to be displayed.
 * @param onShowDetails Callback to navigate to the PodcastDetailsScreen when the item is clicked.
 */

@Composable
fun PodcastItem(podcast: Podcast, onShowDetails: (Podcast) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onShowDetails(podcast) }
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(podcast.image),
            contentDescription = "Podcast Thumbnail",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(12.dp)) // Applies rounded corners to the image
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            // Podcast title
            Text(
                text = podcast.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            // Publisher name with italic styling
            Text(
                text = podcast.publisher,
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray
            )
        }
    }
}