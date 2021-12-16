package com.example.bestcatphotos.view

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import com.example.bestcatphotos.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_layout, menu)
//        return true
//    }
////    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
////        inflater.inflate(R.menu.menu_layout, menu)
////    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.down -> CatPhotoFragment().showCountWindow()
//
////            R.id.down -> CatPhotoFragment().showCountWindow(this)//showCountWindow(CatPhotoFragment().requireContext())
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    public fun showCountWindow() {
//        val dialogView = LayoutInflater.from(this).inflate(R.layout.count_window, null)
//        val builder = AlertDialog.Builder(this)
//            .setView(dialogView)
//        val alertDialog = builder.show()
//        dialogView.findViewById<Button>(R.id.choose_count_button).setOnClickListener {
//            val photoCount = dialogView.findViewById<EditText>(R.id.count_edit_text).text.toString()
//            if (photoCount != "") {
////                showPhotosInCount(photoCount)
//                alertDialog.dismiss()
//            }
//        }
//    }
}