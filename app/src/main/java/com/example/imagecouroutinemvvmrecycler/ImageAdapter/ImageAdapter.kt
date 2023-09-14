package com.example.imagecouroutinemvvmrecycler.ImageAdapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagecouroutinemvvmrecycler.R

class ImageAdapter(private val imageUris: List<Uri>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageUri = imageUris[position]
        holder.bind(imageUri)
    }

    override fun getItemCount(): Int {
        return imageUris.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(imageUri: Uri) {
            Glide.with(itemView.context)
                .load(imageUri)
                .into(imageView)
        }
    }
}
