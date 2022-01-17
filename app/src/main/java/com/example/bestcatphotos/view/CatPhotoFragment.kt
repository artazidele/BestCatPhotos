package com.example.bestcatphotos.view

import android.R.attr
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.bestcatphotos.CatPhotoGridAdapter
import com.example.bestcatphotos.CatPhotoViewModel
import com.example.bestcatphotos.R
import com.example.bestcatphotos.databinding.FragmentCatPhotoBinding
import android.R.attr.fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation


class CatPhotoFragment : Fragment() {

    private val viewModel: CatPhotoViewModel by viewModels()
    var _binding: FragmentCatPhotoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatPhotoBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.photosGrid.adapter = CatPhotoGridAdapter()
        binding.photosGrid.visibility = View.INVISIBLE
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_layout, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.find_item -> showCountWindow()
            R.id.find_votes -> showMyVotes()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showCountWindow()
    }

    private fun showCountWindow() {
    val dialogView = LayoutInflater.from(context).inflate(R.layout.count_window, null)
        val builder = AlertDialog.Builder(context)
            .setView(dialogView)
        val alertDialog = builder.show()
        dialogView.findViewById<Button>(R.id.choose_count_button).setOnClickListener {
            val photoCount = dialogView.findViewById<EditText>(R.id.count_edit_text).text.toString()
            if (photoCount != "") {
                if (photoCount.toInt() > 20 || photoCount.toInt() < 1) {
                    displayErrorMessage()
                } else {
                    showPhotosInCount(photoCount)
                    alertDialog.dismiss()
                }
            }
        }
    }

    private fun showMyVotes() {
        val action = CatPhotoFragmentDirections.actionCatPhotoFragmentToMyVoteFragment()
        findNavController().navigate(action)
    }

    private fun displayErrorMessage() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.count_error_window, null)
        val builder = AlertDialog.Builder(context)
            .setView(dialogView)
        val alertDialog = builder.show()
        dialogView.findViewById<Button>(R.id.okay_button).setOnClickListener {
            alertDialog.dismiss()
        }
    }

    private fun showPhotosInCount(count: String) {
        viewModel.getCatPhotos(count)
        binding.photosGrid.visibility = View.VISIBLE
    }
}