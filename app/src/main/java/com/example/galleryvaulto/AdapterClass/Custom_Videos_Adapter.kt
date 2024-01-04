package com.example.galleryvaulto.AdapterClass

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.net.Uri
import android.text.format.Time
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.galleryvaulto.ModelClass.ItemsViewModel
import com.example.galleryvaulto.R
import com.example.galleryvaulto.databinding.VideoCardViewDesignBinding
import java.util.concurrent.TimeUnit
import kotlin.coroutines.coroutineContext


class Custom_Videos_Adapter(private val mList: ArrayList<ItemsViewModel>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<Custom_Videos_Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_card_view_design, parent, false)
        return ViewHolder(binding = VideoCardViewDesignBinding.bind(view))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]

        // Convert the String path to Uri
      val videoUri : Uri = Uri.parse(itemsViewModel.imagePath)

        Glide.with(holder.videoView.context)
            .load(videoUri.toString())
            .listener(object : RequestListener<Drawable> {
                // ... (your Glide listener code)
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.e("Glide", "Image load failed: ${e?.message}")
                    return true
                }


                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {

                    return false
                }
            })
            .into(holder.videoView)
        GetVideoDuration(holder.itemView.context,holder.textView,videoUri)

        // You can also set an OnClickListener to handle clicks on the VideoView
        holder.videoView.setOnClickListener {
            // Handle the click event if needed
            listener.onItemClick(position)

        }
    }

    class ViewHolder(private val binding: VideoCardViewDesignBinding) : RecyclerView.ViewHolder(binding.root) {
        val videoView: ImageView = binding.Videoview222
        val textView: TextView = binding.videoTime
    }

    fun GetVideoDuration(context: Context, textView: TextView, videoUri: Uri) {
        val mp = MediaPlayer()
        try {
            mp.setDataSource(context, videoUri)
            mp.setOnPreparedListener { mediaPlayer ->
                val duration = mediaPlayer.duration
                mediaPlayer.release()
                /*convert millis to appropriate time*/
                val hours = TimeUnit.MILLISECONDS.toHours(duration.toLong())
                val minutes  = TimeUnit.MILLISECONDS.toMinutes(duration.toLong()) % 60
                val seconds = TimeUnit.MILLISECONDS.toSeconds(duration.toLong()) % 60

                val formattedMinutes  = if (minutes < 10) "0$minutes" else "$minutes"
                val formattedHours = if (hours < 10) "0$hours" else "$hours"
                val formattesSeconds = if(seconds < 10) "0$seconds" else "$seconds"

                val formattedDuration: String = when {
                    hours > 0 -> String.format(" %s:%s:%s", formattedHours, formattedMinutes, formattesSeconds)
                    else -> String.format(" %s:%s", formattedMinutes, formattesSeconds)
                }

                textView.text = formattedDuration
            }
            mp.prepareAsync()
        } catch (e: Exception) {
            e.printStackTrace()
            mp.release()
        }
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


}

