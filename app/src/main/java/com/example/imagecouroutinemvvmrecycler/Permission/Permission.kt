package com.example.imagecouroutinemvvmrecycler.Permission


import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.imagecouroutinemvvmrecycler.Image.ImagePicker

const val READ_EXTERNAL_STORAGE_PERMISSION_REQUEST = 101

fun checkAndRequestPermission(activity: ImagePicker): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val permission = Manifest.permission.READ_EXTERNAL_STORAGE
        val granted = PackageManager.PERMISSION_GRANTED

        if (ContextCompat.checkSelfPermission(activity, permission) != granted) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(permission),
                READ_EXTERNAL_STORAGE_PERMISSION_REQUEST
            )
            return false
        }
    }
    return true
}
