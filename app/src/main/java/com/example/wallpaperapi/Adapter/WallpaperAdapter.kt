package com.example.wallpaperapi.Adapter

import android.app.Dialog
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.wallpaperapi.PhotosItem
import com.example.wallpaperapi.R
import java.net.URL
import javax.sql.DataSource


class WallpaperAdapter(data: ArrayList<PhotosItem>) : Adapter<WallpaperAdapter.WallpaperHolder>() {

    var data = data
    lateinit var context : Context

    class WallpaperHolder(itemView: View) : ViewHolder(itemView) {

        var img = itemView.findViewById<ImageView>(R.id.rcvImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperHolder {
        context = parent.context
        return WallpaperHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.wallpaper_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data?.size!!
    }

    override fun onBindViewHolder(holder: WallpaperHolder, position: Int) {
        Glide.with(holder.itemView.context).load(data?.get(position)?.src?.portrait)
            .into(holder.img)

        holder.itemView.setOnClickListener {

            var dialog = Dialog(holder.itemView.context)
            dialog.setContentView(R.layout.dialog_layout)

            var img = dialog.findViewById<ImageView>(R.id.imgDialog)
            var btn = dialog.findViewById<Button>(R.id.btnDialog)

            Glide.with(holder.itemView.context).load(data.get(position).src?.portrait).into(img)

            btn.setOnClickListener {

//                To set the wallpaper
                Glide.with(context)
                    .asBitmap().load(data.get(position).src?.portrait)
                    .listener(object : RequestListener<Bitmap> {

                        override fun onResourceReady(
                            resource: Bitmap?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            dataSource: com.bumptech.glide.load.DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {

                            val wallpaperManager = WallpaperManager.getInstance(context)
                            try {

                                wallpaperManager.setBitmap(resource)

                            } catch (e: Exception) {
                                e.printStackTrace()
                            }

                            return false
                        }

                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }
                    }).submit()

            }
            dialog.show()
        }
    }


}
