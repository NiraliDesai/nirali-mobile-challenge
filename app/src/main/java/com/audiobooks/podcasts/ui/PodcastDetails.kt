package com.audiobooks.podcasts.ui

import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberAsyncImagePainter
import com.audiobooks.podcasts.model.Podcast

/**
 * Composable function to display the details of a selected podcast.
 * This screen shows the podcast title, image, publisher, description, and a favourite button.
 *
 * @param podcast The selected podcast to display.
 * @param onBack Callback to handle back navigation.
 */
@Composable
fun PodcastDetailsScreen(podcast: Podcast, onBack: () -> Unit) {
    var isFavourite by remember { mutableStateOf(false) }

    // Enables scrolling if content is too long
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            // Back Button (Arrow + Text)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { onBack() } // Navigates back when clicked
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Back",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onBack() }
                )
            }

            // Podcast Title & Publisher
            Text(
                text = podcast.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = podcast.publisher,
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 20.dp, top = 6.dp)
            )

            // Podcast Thumbnail Image
            Image(
                painter = rememberAsyncImagePainter(podcast.image),
                contentDescription = "Podcast Thumbnail",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(240.dp)
                    .clip(RoundedCornerShape(16.dp)) // Adds rounded corners to image
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Favourite Button - Toggles state on click
            Button(
                onClick = { isFavourite = !isFavourite },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFavourite) Color.Blue else Color.Red
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .wrapContentSize()
            ) {
                Text(
                    text = if (isFavourite) "Favourited" else "Favourite",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Podcast Description - Renders HTML content
        item {
            ShowDescription(podcast.description)
        }
    }

}

/**
 * Composable function to display the podcast description.
 * This function uses [AndroidView] to correctly render HTML-formatted text.
 *
 * @param descriptionText The HTML content of the podcast description.
 */

@Composable
fun ShowDescription(descriptionText: String) {
    AndroidView(
        factory = { ctx ->
            TextView(ctx).apply {
                text =
                    htmlToSpanned(descriptionText) // At the time of Display: it was showing some HTML tags
                textSize = 15f
                setTextColor(android.graphics.Color.GRAY)
                textAlignment = View.TEXT_ALIGNMENT_VIEW_START
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
}

/**
 * Converts HTML text to a [Spanned] object, enabling proper rendering of bold, italic, and line breaks.
 *
 * @param htmlText The HTML-formatted string.
 * @return A [Spanned] object that preserves text formatting.
 */
fun htmlToSpanned(htmlText: String): Spanned {
    return Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)
}