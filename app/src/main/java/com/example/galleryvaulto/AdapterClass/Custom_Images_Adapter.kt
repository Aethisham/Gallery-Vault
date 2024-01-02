import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.galleryvaulto.ModelClass.ItemsViewModel
import com.example.galleryvaulto.R

class Custom_Images_Adapter(private val mList: ArrayList<ItemsViewModel>, private val listener: OnItemClickListener) : RecyclerView.Adapter<Custom_Images_Adapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        // Convert the String path to Uri
        val imageUri = Uri.parse(itemsViewModel.imagePath)
        // Use Glide to load the image into the ImageView

        Glide.with(holder.imageView2.context)
            .load(imageUri.toString())
            .listener(object : RequestListener<Drawable> {
                // ... (your Glide listener code)
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.e("Glide", "Image load failed: ${e?.message}")
                    return false

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
            .into(holder.imageView2)

        // Set the click listener on the itemView
        holder.itemView.setOnClickListener {
            // Call the onItemClick method of the listener
            listener.onItemClick(position)
        }
    }

    // return the number of items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView2: ImageView = itemView.findViewById(R.id.imageview222)
    }

    // Define the interface outside the class
    interface OnItemClickListener {
        fun onItemClick(position: Int)

     }
}

