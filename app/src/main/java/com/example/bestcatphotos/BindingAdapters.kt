package com.example.bestcatphotos

import android.view.View
import android.widget.Button
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
import com.example.bestcatphotos.viewmodel.MyVoteViewModel
import com.example.bestcatphotos.viewmodel.VoteStatus
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
//@BindingAdapter("catApiStatus")
//fun bindStatus(statusImageView: ImageView, status: CatApiStatus?) {
//    when (status) {
//        CatApiStatus.LOADING -> {
//            statusImageView.visibility = View.GONE
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
@BindingAdapter("catApiStatus")
fun bindStatus(statusTextView: TextView, status: CatApiStatus?) {
    when (status) {
        CatApiStatus.LOADING -> {
            statusTextView.visibility = View.VISIBLE
            statusTextView.text = "Loading..."
        }
        CatApiStatus.ERROR -> {
            statusTextView.visibility = View.VISIBLE
            statusTextView.text = "No Internet connection."
        }
        CatApiStatus.DONE -> {
            statusTextView.visibility = View.GONE
        }
    }
}
@BindingAdapter("catApiIconStatus")
fun bindIconStatus(statusTextView: Button, status: CatApiStatus?) {
    when (status) {
        CatApiStatus.LOADING -> {
            statusTextView.visibility = View.GONE
        }
        CatApiStatus.ERROR -> {
            statusTextView.visibility = View.VISIBLE
        }
        CatApiStatus.DONE -> {
            statusTextView.visibility = View.GONE
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
@BindingAdapter("imageIdUrl")
fun bindImageId(imgView: ImageView, imgId: String?) {
    imgId?.let {
        val imgUri = imgId.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.circle_animation)
            error(R.drawable.pet_icon)
        }
    }
}
@BindingAdapter("voteImage")
fun bindVoteImage(button: FloatingActionButton, value: Int) {
    if (value == 0) {
        button.setImageResource(R.drawable.thumb_down_icon)
    } else {
        button.setImageResource(R.drawable.thumb_up_icon)
    }
}
@BindingAdapter("voteStatus")
fun bindVoteStatus(statusTextView: TextView, status: VoteStatus?) {
    when (status) {
        VoteStatus.LOADING -> {
            statusTextView.visibility = View.VISIBLE
            statusTextView.text = "Loading..."
        }
        VoteStatus.ERROR -> {
            statusTextView.visibility = View.VISIBLE
            statusTextView.text = "No Internet connection."
        }
        VoteStatus.DONE -> {
            statusTextView.visibility = View.GONE
        }
    }
}
@BindingAdapter("voteIconStatus")
fun bindVoteIconStatus(statusTextView: Button, status: VoteStatus?) {
    when (status) {
        VoteStatus.LOADING -> {
            statusTextView.visibility = View.GONE
        }
        VoteStatus.ERROR -> {
            statusTextView.visibility = View.VISIBLE
        }
        VoteStatus.DONE -> {
            statusTextView.visibility = View.GONE
        }
    }
}
