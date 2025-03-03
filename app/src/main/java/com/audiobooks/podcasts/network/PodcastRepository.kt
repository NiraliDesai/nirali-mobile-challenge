package com.audiobooks.podcasts.network

import com.audiobooks.podcasts.model.Podcast

/**
 * Repository class is responsible for fetching podcast data from the API.
 *
 * This class acts as a data source, retrieving data from the [PodcastApi]
 * and handling errors gracefully using Kotlin's [Result] wrapper.
 *
 * @param service The API service used to fetch podcasts. Defaults to [ApiService.service].
 */

class PodcastRepository(
    private val service: PodcastApi = ApiService.service
) {
    /**
     * Fetches a list of podcasts from the API.
     *
     * This function makes a suspend API call to getBestPodcasts(),
     * retrieves the list of podcasts, and returns a Result-wrapped List.
     *
     * @return A [Result] containing a list of [Podcast] objects if successful, or an exception if failed.
     */
    suspend fun getPodcasts(): Result<List<Podcast>> {
        return try {

            // Calls the API to fetch the best podcasts
            val response = service.getBestPodcasts()

            // If successful, return the list of podcasts : List<Podcast>
            Result.success(response.podcasts)

        } catch (e: Exception) {
            // If an error occurs, return it as a failure
            Result.failure(e)
        }
    }
}
