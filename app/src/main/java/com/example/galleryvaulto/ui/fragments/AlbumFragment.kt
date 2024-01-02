package com.example.galleryvaulto.ui.fragments

import Custom_Images_Adapter
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryvaulto.ModelClass.ItemsViewModel
import com.example.galleryvaulto.databinding.FragmentAlbumBinding


// ... (other imports)

private lateinit var binding: FragmentAlbumBinding

class AlbumFragment : Fragment(), Custom_Images_Adapter.OnItemClickListener {
    private val PICK_IMAGE_REQUEST = 1
    private var selectedImageUri: Uri? = null
    private val imageData = ArrayList<ItemsViewModel>()
    private lateinit var adapter: Custom_Images_Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(layoutInflater)
        val view = binding.root


        binding.imageRecyclerview.layoutManager =
            GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)

        adapter = Custom_Images_Adapter(imageData,this) // Pass 'this' as the OnItemClickListener


        binding.imageRecyclerview.setAdapter(adapter)
        fetchImages()

        return view
    }

    private fun fetchImages() {
        // Use ImagesGallery to get all
        val imagesList = ImagesGallery.listOfImages(requireContext())

        for (imagePath in imagesList) {
            Log.d("Image Path", imagePath)
            val item = ItemsViewModel(imagePath)
            imageData.add(item)
        }

        adapter.notifyDataSetChanged()
    }

    override fun onItemClick(position: Int) {
        var finalposition = position+1
        // Handle item click here
        Toast.makeText(context, "Item clicked at position $finalposition", Toast.LENGTH_SHORT).show()
    }
}
