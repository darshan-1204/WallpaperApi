package com.example.wallpaperapi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.wallpaperapi.PhotosItem
import com.example.wallpaperapi.R
import com.example.wallpaperapi.Src

class WallpaperAdapter(data: ArrayList<PhotosItem>) : Adapter<WallpaperAdapter.WallpaperHolder>() {

    var data = data
    class WallpaperHolder(itemView: View) : ViewHolder(itemView){

        var img = itemView.findViewById<ImageView>(R.id.rcvImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperHolder {
        return WallpaperHolder(LayoutInflater.from(parent.context).inflate(R.layout.wallpaper_item,parent,false))
    }

    override fun getItemCount(): Int {
        return data?.size!!
    }

    override fun onBindViewHolder(holder: WallpaperHolder, position: Int) {
        Glide.with(holder.itemView.context).load(data?.get(position)?.src?.portrait).into(holder.img)
    }
}