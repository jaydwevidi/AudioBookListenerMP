package com.example.audiobooklistnermp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.audiobooklistnermp.objects.CategoryObject
import com.example.audiobooklistnermp.databinding.CustomItemRecyclerviewBinding

class CategoriesAdapterRV(
    var dataList:MutableList<CategoryObject>
) : RecyclerView.Adapter<CategoriesAdapterRV.MyViewHolder>() {

    private lateinit var binding : CustomItemRecyclerviewBinding

    inner class MyViewHolder(
        binding: CustomItemRecyclerviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        var t1: TextView = binding.catagoryTextView
        var i1: ImageView = binding.catagoryImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        binding = CustomItemRecyclerviewBinding
            .inflate(LayoutInflater
                .from(parent.context),
            parent,
            false
        )

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.t1.text = dataList[position].title

        Glide.with(holder.itemView)
            .load(dataList[position].imageURL)
            //.circleCrop()
            .into(holder.i1)
    }

    override fun getItemCount(): Int {
        return dataList.size;
    }
}