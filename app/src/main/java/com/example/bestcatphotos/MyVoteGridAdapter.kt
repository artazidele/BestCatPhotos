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
import com.example.bestcatphotos.databinding.MyVoteItemBinding
import com.example.bestcatphotos.model.CatPhoto
import com.example.bestcatphotos.model.MyVote
import com.example.bestcatphotos.model.PhotoResponse
import com.example.bestcatphotos.model.Vote
import com.example.bestcatphotos.viewmodel.MyVoteViewModel

class MyVoteGridAdapter:
ListAdapter<MyVote, MyVoteGridAdapter.MyVoteViewHolder>(DiffCallback) {
    class MyVoteViewHolder(
        private var binding: MyVoteItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(myVote: MyVote, catPhoto: PhotoResponse) {
            binding.myVote = myVote
            binding.executePendingBindings()
//        }
//        fun bindImage(catPhoto: CatPhoto) {
            binding.catPhoto = catPhoto
            binding.viewModel = MyVoteViewModel()//.getPhoto(myVote.imageId)
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
    override fun onBindViewHolder(holder: MyVoteViewHolder, position: Int) {
        val myVote = getItem(position)
//        val catPhoto = MyVoteViewModel().getPhoto(myVote.imageId)
        MyVoteViewModel().getPhoto(myVote.imageId) {
            if (it?.url != null) {
                val photoResponse = PhotoResponse(it.url)
                holder.bind(myVote, photoResponse)
//                Toast.makeText(context, "You rated positive.", Toast.LENGTH_SHORT).show()
//            } else {
//                Log.v(ContentValues.TAG, "ERROR")
            }
        }

//        if (catPhoto != null) {
//            holder.bind(myVote, catPhoto)
//        }
        holder.itemView.setOnClickListener {
            openDeleteVoteWindow(myVote, holder.itemView.context)
        }
    }
    private fun openDeleteVoteWindow(myVote: MyVote, context: Context) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.delete_vote_window, null)
        val builder = AlertDialog.Builder(context)
            .setView(dialogView)
        val alertDialog = builder.show()





        dialogView.findViewById<Button>(R.id.delete_button).setOnClickListener {
            MyVoteViewModel().deleteVote(myVote) {
                if (it?.imageId != null) {
                    Toast.makeText(context, "Your vote is deleted.", Toast.LENGTH_SHORT).show()
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