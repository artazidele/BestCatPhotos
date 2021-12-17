package com.example.bestcatphotos

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
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
//@BindingAdapter("imageUrl")
//fun bindImage(imgView: ImageView, imgUrl: String?) {
//    imgUrl?.let {
//        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
//        imgView.load(imgUri) {
//            placeholder(R.drawable.loading_animation)
//            error(R.drawable.ic_broken_image)
//        }
//    }
//}
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
//@BindingAdapter("catApiStatus")
//fun bindStatus(statusImageView: ImageView, status: CatApiStatus?) {
//    when (status) {
//        CatApiStatus.LOADING -> {
//            statusImageView.visibility = View.VISIBLE
//            statusImageView.setImageResource(R.drawable.loading_animation)
//        }
//        CatApiStatus.ERROR -> {
//            statusImageView.visibility = View.VISIBLE
//            statusImageView.setImageResource(R.drawable.ic_connection_error)
//        }
//        CatApiStatus.DONE -> {
//            statusImageView.visibility = View.GONE
//        }
//    }
//}


//@BindingAdapter("photoLoading")
//fun bindProgressBar(progressBar: ProgressBar, ready: PhotoReady?) {
//    if (ready == PhotoReady.YES) {
//        progressBar.visibility = View.GONE
//    } else {
//        progressBar.visibility = View.VISIBLE
//    }
//}
@BindingAdapter("catApiStatus")
fun bindStatus(statusProgressBar: ProgressBar, status: CatApiStatus?) {
    when (status) {
        CatApiStatus.LOADING -> {
            statusProgressBar.visibility = View.GONE
//            progressBar.visibility = View.VISIBLE
//            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        CatApiStatus.ERROR -> {
            statusProgressBar.visibility = View.VISIBLE
            //statusProgressBar.setImageResource(R.drawable.ic_connection_error)
        }
        CatApiStatus.DONE -> {
            statusProgressBar.visibility = View.GONE
//            progressBar.visibility = View.INVISIBLE
        }
    }
}
//@BindingAdapter("catApiStatus")
//fun bindStatus(statusImageView: ImageView, status: CatApiStatus?) { //fun bindStatus(progressBar: ProgressBar, statusImageView: ImageView, status: CatApiStatus?) {
//    when (status) {
//        CatApiStatus.LOADING -> {
//            statusImageView.visibility = View.GONE
////            progressBar.visibility = View.VISIBLE
////            statusImageView.setImageResource(R.drawable.loading_animation)
//        }
//        CatApiStatus.ERROR -> {
//            statusImageView.visibility = View.VISIBLE
//            statusImageView.setImageResource(R.drawable.ic_connection_error)
//        }
//        CatApiStatus.DONE -> {
//            statusImageView.visibility = View.GONE
////            progressBar.visibility = View.INVISIBLE
//        }
//    }
//}
