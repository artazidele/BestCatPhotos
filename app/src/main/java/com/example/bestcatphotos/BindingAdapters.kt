package com.example.bestcatphotos

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.bestcatphotos.model.CatPhoto
import com.example.bestcatphotos.model.MyVote
import com.example.bestcatphotos.model.Vote

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CatPhoto>?) {
    val adapter = recyclerView.adapter as CatPhotoGridAdapter
    adapter.submitList(data)
}
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.circle_animation)
            error(R.drawable.pet_icon)
        }
    }
}
@BindingAdapter("catApiStatus")
fun bindStatus(statusImageView: ImageView, status: CatApiStatus?) { //fun bindStatus(progressBar: ProgressBar, statusImageView: ImageView, status: CatApiStatus?) {
    when (status) {
        CatApiStatus.LOADING -> {
            statusImageView.visibility = View.GONE
        }
        CatApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        CatApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
@BindingAdapter("voteText")
fun bindMyVote(textView: TextView, text: String?) {
    textView.text = text
}
@BindingAdapter("voteData")
fun bindVoteRecyclerView(recyclerView: RecyclerView, data: List<MyVote>?) {
    val adapter = recyclerView.adapter as MyVoteGridAdapter
    adapter.submitList(data)
}
