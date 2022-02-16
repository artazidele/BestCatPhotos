package com.example.bestcatphotos

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.NotificationCompat.getColor
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bestcatphotos.databinding.MyVoteItemBinding
import com.example.bestcatphotos.model.CatPhoto
import com.example.bestcatphotos.model.MyVote
import com.example.bestcatphotos.model.PhotoResponse
import com.example.bestcatphotos.model.Vote
import com.example.bestcatphotos.view.MyVoteFragment
import com.example.bestcatphotos.viewmodel.MyVoteViewModel
import com.google.android.material.color.MaterialColors.getColor
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.internal.notify

class MyVoteGridAdapter: //class MyVoteGridAdapter(private val data: ArrayList<MyVote>)://class MyVoteGridAdapter(data: LiveData<MyVote>):
ListAdapter<MyVote, MyVoteGridAdapter.MyVoteViewHolder>(DiffCallback) {
    class MyVoteViewHolder(
        private var binding: MyVoteItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(myVote: MyVote, catPhoto: PhotoResponse) {
            binding.myVote = myVote
            binding.executePendingBindings()
            binding.catPhoto = catPhoto
            binding.viewModel = MyVoteViewModel()
            binding.executePendingBindings()
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<MyVote>() {
        override fun areItemsTheSame(oldItem: MyVote, newItem: MyVote): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MyVote, newItem: MyVote): Boolean {
            return oldItem.id == newItem.id
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyVoteViewHolder {
        return MyVoteViewHolder(
            MyVoteItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

//    private fun getDataCount(): Int = data.size
    override fun getItemCount(): Int {
//        return data.size
        return super.getItemCount()
    }
    override fun onBindViewHolder(holder: MyVoteViewHolder, position: Int) {
        val myVote = getItem(position)
        MyVoteViewModel().getPhoto(myVote.imageId) {
            if (it?.url != null) {
                val photoResponse = PhotoResponse(it.url)
                holder.bind(myVote, photoResponse)
                holder.itemView.setOnClickListener {
                    openImageWindow(photoResponse, holder.itemView.context)
                }
            }
        }
        holder.itemView.findViewById<FloatingActionButton>(R.id.delete_fab).setOnClickListener {
            openDeleteVoteWindow(position, myVote, holder.itemView.context, holder)
        }
    }
    private fun openImageWindow(photoResponse: PhotoResponse, context: Context) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.just_photo_window, null)
        val builder = AlertDialog.Builder(context)
            .setView(dialogView)
        val alertDialog = builder.show()
        val catImage = dialogView.findViewById<ImageView>(R.id.cat_image_view)
        Glide.with(context)
            .load(photoResponse.url)
            .into(catImage)
        dialogView.findViewById<Button>(R.id.cancel_button).setOnClickListener {
            alertDialog.dismiss()
        }
    }
    private fun openDeleteVoteWindow(position: Int, myVote: MyVote, context: Context, holder: RecyclerView.ViewHolder) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.delete_vote_window, null)
        val builder = AlertDialog.Builder(context)
            .setView(dialogView)
        val alertDialog = builder.show()
        dialogView.findViewById<Button>(R.id.delete_button).setOnClickListener {
            MyVoteViewModel().deleteVote(myVote) {
                if (it?.imageId != null) {
//                    data.removeAt(position)
//                    notifyItemRemoved(position)
//                    synchronized(holder.itemView){
////                        holder.itemView.notify()
//                    }

                    holder.itemView.findViewById<FloatingActionButton>(R.id.vote_fab).visibility = View.GONE
                    holder.itemView.findViewById<FloatingActionButton>(R.id.delete_fab).visibility = View.GONE
                    holder.itemView.findViewById<ImageView>(R.id.cat_image).setImageResource(R.drawable.delete_icon)
                    Toast.makeText(context, "Your vote is deleted.", Toast.LENGTH_SHORT).show()
                    alertDialog.dismiss()
                } else {
                    Log.v(ContentValues.TAG, "ERROR")
                }
            }
        }
        dialogView.findViewById<Button>(R.id.cancel_delete_button).setOnClickListener {
            alertDialog.dismiss()
        }
        dialogView.findViewById<Button>(R.id.cancel_button).setOnClickListener {
            alertDialog.dismiss()
        }
    }
}
