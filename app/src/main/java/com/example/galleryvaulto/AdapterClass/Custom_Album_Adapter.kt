package com.example.galleryvaulto.AdapterClass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryvaulto.ModelClass.ItemsViewModel
import com.example.galleryvaulto.ModelClass.ImageDirectoriesList
import com.example.galleryvaulto.R
import com.example.galleryvaulto.databinding.AlbumCardViewDesignBinding

class Custom_Album_Adapter (private val mList: ArrayList<ImageDirectoriesList>) :
    RecyclerView.Adapter<Custom_Album_Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = AlbumCardViewDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val itemsViewModel = mList[position]
            binding.folderName.text = itemsViewModel.folderName
        }
    }

    override fun getItemCount(): Int {
        return mList.size

    }

    class ViewHolder (val binding: AlbumCardViewDesignBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}