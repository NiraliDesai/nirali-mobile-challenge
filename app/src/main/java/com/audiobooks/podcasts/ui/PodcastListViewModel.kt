package com.audiobooks.podcasts.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.audiobooks.podcasts.model.Podcast
import com.audiobooks.podcasts.network.PodcastRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PodcastListViewModel : ViewModel() {

    private val repository = PodcastRepository() // Repository instance to fetch podcast data
    private val _podcasts = MutableStateFlow<List<Podcast>>(emptyList()) // This will hold the data
    val podcasts: StateFlow<List<Podcast>> = _podcasts // Publicly exposed immutable StateFlow to observe podcasts

    init {
        fetchPodcasts()
    }

    /**
     * Fetches the list of podcasts from the repository.
     * This method runs inside a coroutine to avoid blocking the UI thread.
     */

    private fun fetchPodcasts() {

        viewModelScope.launch {
            try {
                val result = repository.getPodcasts()
                if (result.isSuccess) {
                    val podcastList = result.getOrDefault(emptyList())
                    _podcasts.value = podcastList
                } else {
                    // Handle any errors that occur while fetching data
                    result.exceptionOrNull()?.printStackTrace()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
