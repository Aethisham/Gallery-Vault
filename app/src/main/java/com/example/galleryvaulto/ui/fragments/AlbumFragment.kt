package com.example.galleryvaulto.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryvaulto.AdapterClass.Custom_Album_Adapter
import com.example.galleryvaulto.GetImagesFoldersAndTheirPaths
import com.example.galleryvaulto.ModelClass.ImageDirectoriesList
import com.example.galleryvaulto.ModelClass.ItemsViewModel
import com.example.galleryvaulto.R
import com.example.galleryvaulto.databinding.FragmentAlbumBinding
import com.example.galleryvaulto.databinding.FragmentImagesBinding


class AlbumFragment : Fragment() {

    private val imageDirectoriesList: ArrayList<ImageDirectoriesList> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    private lateinit var adapter: Custom_Album_Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragments
        val binding: FragmentAlbumBinding = FragmentAlbumBinding.inflate(layoutInflater)
        val view = binding.root

        binding.albumRecyclerview.layoutManager =
            GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)

        adapter = Custom_Album_Adapter(imageDirectoriesList)

        binding.albumRecyclerview.setAdapter(adapter)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val files = GetImagesFoldersAndTheirPaths(requireContext())
        val pairsList: List<Pair<String, String>> = files.getImageDirectories(requireContext())

        for (imageDirectory in pairsList) {
            val folderName = imageDirectory.second
            if (folderName != null) {
                imageDirectoriesList.add(ImageDirectoriesList(folderName))
            }
        }
        adapter.notifyDataSetChanged()
    }


}