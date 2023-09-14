package com.example.imagecouroutinemvvmrecycler.Image


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagecouroutinemvvmrecycler.ImageAdapter.ImageAdapter
import com.example.imagecouroutinemvvmrecycler.R
import com.example.imagecouroutinemvvmrecycler.ViewModel.ImageViewModel


@Suppress("DEPRECATION")
class ImagePicker : AppCompatActivity() {

    private lateinit var selectedImageView: ImageView

    private lateinit var viewModel: ImageViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        setContentView(R.layout.activity_main)

        selectedImageView = findViewById(R.id.selectedImageView)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ImageAdapter(viewModel.selectedImageUris)
        recyclerView.adapter = adapter

        val selectImageButton = findViewById<Button>(R.id.selectImageButton)
        selectImageButton.setOnClickListener {
            viewModel.openGallery(this)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.handleActivityResult(requestCode, resultCode, data)
        adapter.notifyDataSetChanged() // Update the RecyclerView when new images are added
    }
}




//    private fun openGallery() {
//        // Check and request permission if needed
//        if (checkAndRequestPermission(this)) {
//            // Permission already granted, open the gallery
//            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            startActivityForResult(intent, GALLERY_REQUEST_CODE)
//        }
//    }
//

//        private fun openGallery() {
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.type = "image/*"
//        startActivityForResult(intent, GALLERY_REQUEST_CODE)
//    }
