package com.example.galleryvaulto.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryvaulto.AdapterClass.Custom_Videos_Adapter
import com.example.galleryvaulto.ModelClass.ItemsViewModel
import com.example.galleryvaulto.VideosGallery
import com.example.galleryvaulto.databinding.FragmentVideosBinding

class VideosFragment : Fragment() {

    private lateinit var binding: FragmentVideosBinding
    private val videoData = ArrayList<ItemsViewModel>()
    private lateinit var videosAdapter: Custom_Videos_Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVideosBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.videoRecyclerview.layoutManager =
            GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)

        videosAdapter = Custom_Videos_Adapter(videoData)
        binding.videoRecyclerview.adapter = videosAdapter

        fetchVideos()

        return view
    }

    private fun fetchVideos() {
        val videosList = VideosGallery.listOfVideos(requireContext())

        for (videoPath in videosList) {
            Log.d("Video Path", videoPath)
            val item = ItemsViewModel(videoPath)
            videoData.add(item)
        }
        videosAdapter.notifyDataSetChanged()
    }
}
