package com.example.bestcatphotos.view

import android.app.AlertDialog
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bestcatphotos.CatPhotoGridAdapter
import com.example.bestcatphotos.MyVoteGridAdapter
import com.example.bestcatphotos.R
import com.example.bestcatphotos.databinding.MyVoteFragmentBinding
import com.example.bestcatphotos.model.Vote
import com.example.bestcatphotos.viewmodel.MyVoteViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MyVoteFragment : Fragment() {
    private val viewModel: MyVoteViewModel by viewModels()
    var _binding: MyVoteFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MyVoteFragmentBinding.inflate(inflater)
        val user = Firebase.auth.currentUser
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.getMyVotes(user?.uid.toString())
        binding.votesGrid.adapter = MyVoteGridAdapter()
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_layout, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.find_item -> toPhotoFragment()
            R.id.log_out -> logOut()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logOut() {
        Firebase.auth.signOut()
        val action = MyVoteFragmentDirections.actionMyVoteFragmentToLogInFragment()
        findNavController().navigate(action)
    }

    private fun toPhotoFragment() {
        val action = MyVoteFragmentDirections.actionMyVoteFragmentToCatPhotoFragment()
        findNavController().navigate(action)
    }
}
