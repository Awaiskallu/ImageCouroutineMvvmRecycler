package com.example.imagecouroutinemvvmrecycler.Image

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.imagecouroutinemvvmrecycler.Permission.READ_EXTERNAL_STORAGE_PERMISSION_REQUEST
import com.example.imagecouroutinemvvmrecycler.R
import com.example.imagecouroutinemvvmrecycler.ViewModel.ImageViewModel


@Suppress("DEPRECATION")
class ImagePicker : AppCompatActivity() {

    private lateinit var selectedImageView: ImageView

    private lateinit var viewModel: ImageViewModel


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        setContentView(R.layout.activity_main)

        selectedImageView = findViewById(R.id.selectedImageView)

        val selectImageButton = findViewById<Button>(R.id.selectImageButton)
        selectImageButton.setOnClickListener {
            viewModel.openGallery(this)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, you can now open the gallery
                viewModel.openGallery(this)
            } else {
                // Permission denied
                // You can show a message to the user or handle this case as needed
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
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


    // Define GALLERY_REQUEST_CODE as a class constant
    private val GALLERY_REQUEST_CODE = 102


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.handleActivityResult(requestCode, resultCode, data)

        if (viewModel.selectedImageUri != null) {
            // Display the selected image in the ImageView
            selectedImageView.setImageURI(viewModel.selectedImageUri)
            selectedImageView.visibility = ImageView.VISIBLE
        }
    }
}
