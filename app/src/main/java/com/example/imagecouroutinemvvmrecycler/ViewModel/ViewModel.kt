package com.example.imagecouroutinemvvmrecycler.ViewModel

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel

class ImageViewModel : ViewModel() {
    var selectedImageUri: Uri? = null
    private val GALLERY_REQUEST_CODE = 102

    fun openGallery(activity: Activity) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        activity.startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            // Handle the selected image here
            selectedImageUri = data.data
        }
    }
}
