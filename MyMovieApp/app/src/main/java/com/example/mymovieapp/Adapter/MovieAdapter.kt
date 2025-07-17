package com.example.mymovieapp.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.size.Scale
import com.example.mymovieapp.DetailesMovieActivity
import com.example.mymovieapp.R
import com.example.mymovieapp.Response.MovieListResponse
import com.example.mymovieapp.Utils.Constants.POSTER_BASE_URL
import com.example.mymovieapp.databinding.ItemRowBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    private lateinit var binding: ItemRowBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemRowBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: MovieListResponse.Result) {
            binding.apply {
                tvMovieName.text = item.title
                tvRate.text=item.vote_average.toString()
                val moviePosterURL = POSTER_BASE_URL + item.poster_path
                ImgMovie.load(moviePosterURL){
                    crossfade(true)
                    placeholder(R.drawable.poster_placeholder)
                    scale(Scale.FILL)
                }
                tvLang.text=item.original_language
                tvMovieDateReleased.text = item.release_date

                //
                root.setOnClickListener {
                    val intent = Intent(context, DetailesMovieActivity::class.java)
                    intent.putExtra("id", item.id)
                    context.startActivity(intent)
                }
                //
            }

        }
    }

    //
    private fun scale(fill: Scale) {

    }

    private fun placeholder(posterPlaceholder: Int) {

    }

    private fun crossfade(b: Boolean) {

    }
    ///

    private val differCallback = object : DiffUtil.ItemCallback<MovieListResponse.Result>() {
        override fun areItemsTheSame(oldItem: MovieListResponse.Result, newItem: MovieListResponse.Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieListResponse.Result, newItem: MovieListResponse.Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}

class ImgMovie {
    companion object {
        fun load(moviePosterURL: String, function: () -> Unit) {

        }
    }

}

class MovieDetailesActivity {

}
