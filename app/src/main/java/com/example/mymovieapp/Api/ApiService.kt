package com.example.mymovieapp.Api

import com.example.mymovieapp.Response.DetailesMovieResponse
import com.example.mymovieapp.Response.MovieListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page : Int): Call<MovieListResponse>

    @GET("movie/{movie_id}")
    fun getMoviedetailes(@Path("movie_id") id:Int) : Call<DetailesMovieResponse>

}
