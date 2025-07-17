package com.example.mymovieapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovieapp.Adapter.MovieAdapter
import com.example.mymovieapp.Api.ApiClient
import com.example.mymovieapp.Api.ApiService
import com.example.mymovieapp.Response.MovieListResponse
import com.example.mymovieapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val movieAdapter by lazy { MovieAdapter() }
    private val api: ApiService by lazy {
        ApiClient().getClient().create(ApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            prgBarMovie.visibility = View.VISIBLE
            val callMovieApi = api.getPopularMovie(1)
            callMovieApi.enqueue(object : Callback<MovieListResponse> {
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    prgBarMovie.visibility = View.GONE
                    when (response.code()) {
                        //Successful Responses
                        in 200..299 -> {
                            response.body().let { itBody ->
                                itBody?.results.let { itData ->
                                    if (itData!!.isNotEmpty()) {
                                        movieAdapter.differ.submitList(itData)
                                        rvMovie.apply {
                                            layoutManager = LinearLayoutManager(this@MainActivity)
                                            adapter = movieAdapter
                                        }
                                    }

                                }

                            }
                        }
                        //Redirection Message
                        in 300..399 -> {
                            Log.d("Response Code", "Redirection message :${response.code()}")
                        }
                        //Client error Responses
                        in 400..499 -> {
                            Log.d("Response Code", "Client error responses :${response.code()}")
                        }
                        //Server error Responses
                        in 500..599 -> {
                            Log.d("Response Code", "Server error responses :${response.code()}")
                        }

                    }
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    binding.prgBarMovie.visibility=View.GONE
                    Log.e("onFailure", "Err :${t.message}")


                }


            })
        }


    }
}