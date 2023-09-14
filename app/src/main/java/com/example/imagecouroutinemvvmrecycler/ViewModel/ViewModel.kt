package com.example.imagecouroutinemvvmrecycler.ViewModel

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagecouroutinemvvmrecycler.Image.ImagePicker
import kotlinx.coroutines.launch
import com.example.imagecouroutinemvvmrecycler.Permission.checkAndRequestPermission
import kotlinx.coroutines.delay

class ImageViewModel : ViewModel() {
    var selectedImageUris: MutableList<Uri> = mutableListOf()
    private val GALLERY_REQUEST_CODE = 102


    suspend fun openGallery(activity: Activity) {
        viewModelScope.launch {
        // Check and request permission if needed
        if (checkAndRequestPermission(activity as ImagePicker)) {


            delay(2000)

            // Permission already granted, open the gallery
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activity.startActivityForResult(intent, GALLERY_REQUEST_CODE)
        }

        }

        Log.d(ContentValues.TAG, "Select Image")
    }

    fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            if (data.clipData != null) {
                // Multiple images were selected
                for (i in 0 until data.clipData!!.itemCount) {
                    val imageUri = data.clipData!!.getItemAt(i).uri
                    selectedImageUris.add(imageUri)
                }
            } else if (data.data != null) {
                // Single image was selected
                val imageUri = data.data
                if (imageUri != null) {
                    selectedImageUris.add(imageUri)
                }
            }
        }
    }
}
