package com.boymustafa.doggies.data.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boymustafa.doggies.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_dog.view.*
import kotlin.properties.Delegates

class DogAdapter : RecyclerView.Adapter<DogAdapter.DogViewHolder>(){


    // Our data list is going to be notified when we assign a new list of data to it
    private var dogsUrlList:MutableList<String> by Delegates.observable(mutableListOf()){ _, _, _ ->
        notifyDataSetChanged()
    }


    class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(url:String) {
            // Load images using Glide library
            Glide.with(itemView.context)
                .load(url)
                .centerCrop()
                .thumbnail()
                .into(itemView.itemDogImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_dog,parent,false)
        return DogViewHolder(view)
    }

    override fun getItemCount(): Int = dogsUrlList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val url:String = dogsUrlList[position]
            holder.bind(url)
        }
    }

    // Update recyclerView's data
    fun updateData(newUrlList:MutableList<String>) {
        dogsUrlList = newUrlList
    }

}