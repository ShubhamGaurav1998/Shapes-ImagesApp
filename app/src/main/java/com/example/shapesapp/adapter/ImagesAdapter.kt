package com.example.shapesapp.adapter

import com.example.shapesapp.models.ImageApiResponseItem

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shapesapp.R
import com.example.shapesapp.databinding.RcvRowItemBinding

class ImagesAdapter(private val context: Context) : PagedListAdapter<ImageApiResponseItem, ImagesAdapter.MyViewHolder>(
    USER_COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: RcvRowItemBinding = DataBindingUtil.inflate(inflater, R.layout.rcv_row_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val imageUrl = getItem(position)?.urls?.thumb
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(holder.itemBinding.ivApiImage);
    }

    class MyViewHolder(val itemBinding: RcvRowItemBinding) : RecyclerView.ViewHolder(itemBinding.root){

        private var binding : RcvRowItemBinding? = null

        init {
            this.binding = itemBinding
        }

    }
    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<ImageApiResponseItem>() {
            override fun areItemsTheSame(oldItem: ImageApiResponseItem, newItem: ImageApiResponseItem): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: ImageApiResponseItem, newItem: ImageApiResponseItem): Boolean =
                newItem == oldItem
        }
    }

}