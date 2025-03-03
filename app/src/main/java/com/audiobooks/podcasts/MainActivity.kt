package com.audiobooks.podcasts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.audiobooks.podcasts.model.Podcast
import com.audiobooks.podcasts.navigation.CustomNavType
import com.audiobooks.podcasts.navigation.PodcastDetails
import com.audiobooks.podcasts.navigation.PodcastList
import com.audiobooks.podcasts.ui.PodcastDetailsScreen
import com.audiobooks.podcasts.ui.PodcastListScreen
import com.audiobooks.podcasts.ui.theme.PodcastsTheme
import kotlin.reflect.typeOf


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController() // Creates NavController to manage navigation

            PodcastsTheme {
                // NavHost manages screen navigation and defines routes
                NavHost(
                    navController = navController,
                    startDestination = PodcastList, // The first screen displayed (List of Podcasts)
                    modifier = Modifier.safeDrawingPadding() // This prevents UI overlap with system gestures
                ) {
                    // Podcast List Screen route
                    composable<PodcastList> {
                        PodcastListScreen(
                            onShowDetails = { podcast ->
                                // Navigate to Podcast Details Screen with selected podcast data (Navigation from screen 1 to screen 2)
                                navController.navigate(PodcastDetails(podcast))
                            }
                        )
                    }
                    // Podcast Details Screen route
                    composable<PodcastDetails>(
                        typeMap = mapOf(typeOf<Podcast>() to CustomNavType.PodcastType)
                    ) { backStackEntry ->

                        val route = backStackEntry.toRoute<PodcastDetails>() // Extracts podcast data from route
                        PodcastDetailsScreen(
                            podcast = route.podcast,
                            onBack = { navController.popBackStack() } // Handles back navigation to Podcast List
                        )
                    }
                }
            }
        }
    }
}
