package com.example.galleryvaulto.AdapterClass

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryvaulto.ModelClass.ItemsViewModel
import com.example.galleryvaulto.R

class Custom_Videos_Adapter(private val mList: ArrayList<ItemsViewModel>) :
    RecyclerView.Adapter<Custom_Videos_Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_card_view_design, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]

        // Convert the String path to Uri
        val videoUri = Uri.parse(itemsViewModel.imagePath)

        // Set the video URI to the VideoView
        holder.videoView.setVideoURI(videoUri)

        // Start video playback
        holder.videoView.start()

        // You can also set an OnClickListener to handle clicks on the VideoView
        holder.videoView.setOnClickListener {
            // Handle the click event if needed
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val videoView: VideoView = itemView.findViewById(R.id.Videoview222)
    }
}
