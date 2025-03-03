# Mobile Developer Coding Challenge (Junior)

## The Goal

This is a simple two-screen podcasts app. A basic mockup screenshots are provided below:

## 📸 Screenshots

<div style="text-align: center;">
  <img src="https://github.com/NiraliDesai/nirali-mobile-challenge/blob/main/PodcastListScreen.jpg" alt="Podcast List Screen" width="45%" />
  <img src="https://github.com/NiraliDesai/nirali-mobile-challenge/blob/main/PodcastDetailsScreen.jpg" alt="Podcast Details Screen" width="45%" />
</div>

#### Screen 1

- [ ] Show a list of podcasts using the endpoint provided below.
- [ ] Each list item should show the podcast thumbnail, title, and publisher name. There is <ins>**NO**</ins> need to show the Favourite text shown in the mock for screen 1.

#### Screen 2

- [ ] Tapping on a list item from Screen 1 should bring you to Screen 2.
- [ ] On Screen 2, show the podcast's title, publisher name, thumbnail, and description.
- [ ] Add a Favourite button.
- [ ] The Favourite button should have two states: Favourite and Favourited.
- [ ] When tapping the Favourite button, the label should change to Favourited, and vice-versa. There is <ins>**NO**</ins> need to handle the actual favourite action.

## 🚀 Improvements Implemented

During the development of this challenge, several improvements were made to enhance performance, maintainability, and user experience:

✅ **Jetpack Compose UI Enhancements**
- Used **LazyColumn** for smooth, efficient scrolling of podcast lists.
- Improved **Podcast Details UI** to match the provided design, including proper **padding, spacing, and image scaling**.

✅ **Navigation & State Management**
- Implemented **Jetpack Navigation Compose** for seamless screen transitions.
- Used **StateFlow** in `PodcastListViewModel` for efficient state management.

✅ **Performance Optimizations**
- Used **Coroutines & ViewModelScope** for non-blocking API calls.
- Implemented **Result wrapper** for better error handling in `PodcastRepository`.

✅ **Code Readability & Maintainability**
- Added **structured documentation** to `MainActivity`, `ViewModel`, `Repository`, and UI components.
- Followed **MVVM architecture** to separate concerns between UI, business logic, and data layers.

## Suggested Future Enhancements

Although the app meets the challenge requirements, below are some potential improvements for future iterations:

### UI/UX Enhancements
- Add **Swipe to Refresh** to allow users to reload podcasts.
- Improve **podcast item layout** with better alignment and animations.
- Implement **Dark Mode** support.
- Implement **Pagination** for smooth data scroll

### Networking Improvements
- Add **offline caching** with Room Database or DataStore.

### Favourite Podcast Feature
- Persist **Favourite podcasts** using Room or SharedPreferences.
- Add **Filter & Sorting** options to show Favourited podcasts separately.

### Testing & Code Quality
- Implement **Unit Tests & UI Tests** for ViewModel and Composables.
- Use **Mockk** for testing API responses.
- Improve **logging and analytics** to track user behavior.

## Conclusion
- This project successfully implements a **Jetpack Compose-based podcast app** following modern **Android development practices**. With additional enhancements like caching, testing, and UI refinements, this could be further optimized for production-level quality.
