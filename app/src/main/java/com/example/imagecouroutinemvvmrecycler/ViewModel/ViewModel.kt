import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel

class ImageViewModel : ViewModel() {
    var selectedImageUris: MutableList<Uri> = mutableListOf()
    private val GALLERY_REQUEST_CODE = 102

    fun openGallery(activity: Activity) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true) // Enable multiple image selection
        activity.startActivityForResult(intent, GALLERY_REQUEST_CODE)
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
