package com.ult3mate.photoapp.adapter

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.github.twocoffeesoneteam.glidetovectoryou.GlideApp
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.squareup.picasso.Picasso
import com.ult3mate.photoapp.R
import com.ult3mate.photoapp.ToPhoto
import com.ult3mate.photoapp.databinding.PhotoListBinding
import com.ult3mate.photoapp.modes.JsonResult
import com.ult3mate.photoapp.services.APIClient.getImageURL

class CustomListAdapter(private var photoList: List<JsonResult>?) :
    RecyclerView.Adapter<CustomListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PhotoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding.root, binding)
    }

    override fun getItemCount() = photoList?.size ?: 0

    override fun onBindViewHolder(holder: CustomListAdapter.ViewHolder, position: Int) {
        val binding = holder.binding
        with(binding){
            photoList?.let { list ->
                val item = list[position]
                val title = item.title
                val image_url = item.url
                val thumbnailUrl = item.thumbnailUrl
                binding.PhotoTitle.text = title

                Picasso
                    .get()
                    .load(thumbnailUrl)
                    .into(binding.imageUrl)
                }
        }
    }




    inner class ViewHolder(view: View, val binding: PhotoListBinding) :
        RecyclerView.ViewHolder(view) {
        init {
            binding.myPhoto.setOnClickListener{
                val item = photoList?.let { list ->
                    val item = list[adapterPosition]
                    Intent(view.context,ToPhoto::class.java).apply {
                        putExtra("title",item.title)
                        putExtra("url",item.url)

                    }.run {
                        view.context.startActivity(this)
                    }
                }
            }
        }

    }

}
