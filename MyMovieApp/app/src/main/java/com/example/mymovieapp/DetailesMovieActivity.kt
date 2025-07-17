package com.example.mymovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import coil.load
import coil.size.Scale
import com.example.mymovieapp.Adapter.ImgMovie
import com.example.mymovieapp.Api.ApiClient
import com.example.mymovieapp.Api.ApiService
import com.example.mymovieapp.Response.DetailesMovieResponse
import com.example.mymovieapp.Utils.Constants.POSTER_BASE_URL
import com.example.mymovieapp.databinding.ActivityDetailesMovieBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailesMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailesMovieBinding
    private val api: ApiService by lazy {
        ApiClient().getClient().create(ApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailesMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movieId = intent.getIntExtra("id", 1)
        binding.apply {
            val callDetaileMovieApi = api.getMoviedetailes(movieId)
            callDetaileMovieApi.enqueue(object : Callback<DetailesMovieResponse> {
                override fun onResponse(
                    call: Call<DetailesMovieResponse>,
                    response: Response<DetailesMovieResponse>
                ) {
                    when (response.code()) {
                        in 200..299 -> {
                            response.body().let { itBody ->
                                val imagePoster = POSTER_BASE_URL + itBody!!.poster_path
                                ImgMovie.load(imagePoster) {
                                    crossfade(true)
                                    placeholder(R.drawable.poster_placeholder)
                                    scale(Scale.FILL)
                                }
                                imgBackground.load(imagePoster) {
                                    crossfade(true)
                                    placeholder(R.drawable.poster_placeholder)
                                    scale(Scale.FILL)
                                }

                                tvMovieTitle.text = itBody.title
                                tvMovieTagLine.text = itBody.tagline
                                tvMovieDateRelease.text = itBody.release_date
                                tvMovieRating.text = itBody.vote_average.toString()
                                tvMovieRuntime.text = itBody.runtime.toString()
                                tvMovieBudget.text = itBody.budget.toString()
                                tvMovieRevenue.text = itBody.revenue.toString()
                                tvMovieOverview.text = itBody.overview

                            }
                        }

                        in 300..399 -> {
                            Log.d("Response Code", "Redirection message :${response.code()}")
                        }

                        in 400..499 -> {
                            Log.d("Response Code", "Client error responses :${response.code()}")
                        }

                        in 500..599 -> {
                            Log.d("Response Code", "Server error responses :${response.code()}")
                        }

                    }
                }

                override fun onFailure(call: Call<DetailesMovieResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        }
    }

    private fun placeholder(posterPlaceholder: Int) {

    }

    private fun crossfade(b: Boolean) {

    }

    private fun scale(fill: Scale) {
        TODO("Not yet implemented")
    }
}