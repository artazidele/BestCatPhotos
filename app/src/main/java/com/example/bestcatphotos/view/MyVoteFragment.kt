package com.example.bestcatphotos.view

import android.app.AlertDialog
import android.os.Bundle
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
import com.example.bestcatphotos.viewmodel.MyVoteViewModel

class MyVoteFragment : Fragment() {

    private val viewModel: MyVoteViewModel by viewModels()
    var _binding: MyVoteFragmentBinding? = null//FragmentMyVoteBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MyVoteFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.getMyVotes()
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
        }
        return super.onOptionsItemSelected(item)
    }

    private fun toPhotoFragment() {
        val action = MyVoteFragmentDirections.actionMyVoteFragmentToCatPhotoFragment()
        findNavController().navigate(action)
    }
}
