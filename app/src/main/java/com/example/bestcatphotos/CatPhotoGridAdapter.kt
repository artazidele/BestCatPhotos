package com.example.bestcatphotos

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bestcatphotos.databinding.CatPhotoItemBinding
import com.example.bestcatphotos.model.CatPhoto
import com.example.bestcatphotos.model.Vote
import com.example.bestcatphotos.view.CatPhotoFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CatPhotoGridAdapter :
    ListAdapter<CatPhoto, CatPhotoGridAdapter.CatPhotosViewHolder>(DiffCallback) {
    class CatPhotosViewHolder(
        private var binding: CatPhotoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(catPhoto: CatPhoto) {
            binding.photo = catPhoto
            binding.executePendingBindings()
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<CatPhoto>() {
        override fun areItemsTheSame(oldItem: CatPhoto, newItem: CatPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CatPhoto, newItem: CatPhoto): Boolean {
            return oldItem.url == newItem.url
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatPhotosViewHolder {
        return CatPhotosViewHolder(
            CatPhotoItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }
    override fun onBindViewHolder(holder: CatPhotosViewHolder, position: Int) {
        val catPhoto = getItem(position)
        holder.bind(catPhoto)
        holder.itemView.setOnClickListener {
            openImageWindow(catPhoto, holder.itemView.context)
        }
    }
    private fun openImageWindow(catPhoto: CatPhoto, context: Context) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.rate_cat_photo_window, null)
        val builder = AlertDialog.Builder(context)
            .setView(dialogView)
        val alertDialog = builder.show()
        val catImage = dialogView.findViewById<ImageView>(R.id.cat_image_view)
        Glide.with(context)
            .load(catPhoto.url)
            .into(catImage)
        dialogView.findViewById<FloatingActionButton>(R.id.rate_positive_button).setOnClickListener {
            val user = Firebase.auth.currentUser
            val vote = Vote(catPhoto.id, user?.uid.toString(),  1)
            CatPhotoViewModel().makeVote(vote) {
                if (it?.imageId != null) {
                    Toast.makeText(context, "You rated positive.", Toast.LENGTH_SHORT).show()
                } else {
                    Log.v(ContentValues.TAG, "ERROR")
                }
            }
        }
        dialogView.findViewById<FloatingActionButton>(R.id.rate_negative_button).setOnClickListener {
            val user = Firebase.auth.currentUser
            val vote = Vote(catPhoto.id, user?.uid.toString(),  0)
            CatPhotoViewModel().makeVote(vote) {
                if (it?.imageId != null) {
                    Toast.makeText(context, "You rated negative.", Toast.LENGTH_SHORT).show()
                } else {
                    Log.v(ContentValues.TAG, "ERROR")
                }
            }
        }
        dialogView.findViewById<Button>(R.id.cancel_button).setOnClickListener {
            alertDialog.dismiss()
        }
    }
}
