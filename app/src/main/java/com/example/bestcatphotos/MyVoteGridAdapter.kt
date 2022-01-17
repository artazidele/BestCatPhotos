package com.example.bestcatphotos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bestcatphotos.databinding.MyVoteItemBinding
import com.example.bestcatphotos.model.MyVote

class MyVoteGridAdapter:
ListAdapter<MyVote, MyVoteGridAdapter.MyVoteViewHolder>(DiffCallback) {
    class MyVoteViewHolder(
        private var binding: MyVoteItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(myVote: MyVote) {
            binding.myVote = myVote
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
        holder.bind(myVote)
    }
}