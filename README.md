# MyMovieApp
MyMovieApp is a modern Android application built using Kotlin, MVVM architecture, Retrofit, and Coil that allows users to browse popular movies and view detailed information including the poster, rating, release date, budget, revenue, and more. It consumes TMDB (The Movie Database) API to fetch live data dynamically.

# Features
1. Browse Popular Movies: Loads a paginated list of trending/popular movies.
2. Detailed Movie View: Displays full information for each movie selected, including:
Title & Tagline
Poster Image (with placeholder and smooth loading)
Release Date
Rating
Runtime
Budget & Revenue
Overview/Synopsis
3. Live API Data: Fully powered by The Movie Database (TMDB) RESTful API.
4. Intuitive UI/UX: Clean layout using ConstraintLayout, ShapeableImageView, and RecyclerView.
5. Loading Indicators: Progress bars for network calls to enhance user feedback.

# Architecture
The app follows the MVVM (Model-View-ViewModel) architecture to ensure separation of concerns and maintainability.
1. Model: Data classes like MovieListResponse, DetailesMovieResponse
2. View: Activities (MainActivity, DetailesMovieActivity) and XML layouts
3. ViewModel: Not explicitly used, but Retrofit handles data fetching logic, can be extended in the future.

# API Integration - TMDB
I use TMDB (The Movie Database) API for fetching movie data. 
