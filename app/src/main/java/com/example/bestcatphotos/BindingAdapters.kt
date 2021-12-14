package com.example.bestcatphotos

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.bestcatphotos.model.CatPhoto

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
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}
@BindingAdapter("catApiStatus")
fun bindStatus(statusImageView: ImageView, status: CatApiStatus?) {
    when (status) {
        CatApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
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
