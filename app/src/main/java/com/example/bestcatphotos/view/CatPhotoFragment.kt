package com.example.bestcatphotos.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.bestcatphotos.CatPhotoGridAdapter
import com.example.bestcatphotos.CatPhotoViewModel
import com.example.bestcatphotos.R
import com.example.bestcatphotos.databinding.FragmentCatPhotoBinding


class CatPhotoFragment : Fragment() {

    private val viewModel: CatPhotoViewModel by viewModels()
    var _binding: FragmentCatPhotoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val binding = FragmentCatPhotoBinding.inflate(inflater)
        _binding = FragmentCatPhotoBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.photosGrid.adapter = CatPhotoGridAdapter()
        binding.photosGrid.visibility = View.INVISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.count_window, null)
        val builder = AlertDialog.Builder(context)
            .setView(dialogView)
        val alertDialog = builder.show()
        dialogView.findViewById<Button>(R.id.choose_count_button).setOnClickListener {
            val photoCount = dialogView.findViewById<EditText>(R.id.count_edit_text).text.toString()
            showPhotosInCount(photoCount)
            alertDialog.dismiss()
        }
    }

    private fun showPhotosInCount(count: String) {
        viewModel.getCatPhotos(count)
        binding.photosGrid.visibility = View.VISIBLE
    }
}