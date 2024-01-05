package com.example.galleryvaulto.ui.fragments

import android.os.Bundle
import android.os.Environment
import android.system.Os.accept
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryvaulto.AdapterClass.Custom_Album_Adapter
import com.example.galleryvaulto.GetImagesFoldersAndTheirPaths
import com.example.galleryvaulto.ModelClass.ImageDirectoriesList
import com.example.galleryvaulto.databinding.FragmentAlbumBinding
import java.io.File
import java.util.Collections


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
            Log.d("12335", "onViewCreated: ${imageDirectory.first}",)
            if (folderName != null) {
                imageDirectoriesList.add(ImageDirectoriesList(folderName))
            }
        }
        adapter.notifyDataSetChanged()
    }


//    private fun showImageList() {
//        val file = File("${ Environment.getExternalStorageDirectory()}/WhatsApp Images/")
//        if (file.exists()) {
//            val fileListVideo = file.list()
//            Collections.addAll(arrayList, *fileListVideo)
//        }
//    }

}
