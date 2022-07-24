package com.example.shapesapp.views

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shapesapp.R
import com.example.shapesapp.adapter.ImagesAdapter
import com.example.shapesapp.databinding.ActivityImagesBinding
import com.example.shapesapp.models.ImageApiResponseItem
import com.example.shapesapp.utils.Constants
import com.example.shapesapp.viewmodels.ImageViewModel
import com.example.shapesapp.viewmodels.ImageViewModelFactory

class ImagesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityImagesBinding = DataBindingUtil.setContentView(this, R.layout.activity_images)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        binding.recyclerMain.layoutManager = GridLayoutManager(this, Constants.SPAN_COUNT_FOR_GRIDVIEW)
        val adapter = ImagesAdapter(this)
        binding.recyclerMain.adapter = adapter


        val imagesViewModel = ViewModelProvider(this, ImageViewModelFactory(this)).get(ImageViewModel::class.java)
        imagesViewModel.getData().observe(this, object : Observer<PagedList<ImageApiResponseItem>> {
            override fun onChanged(t: PagedList<ImageApiResponseItem>?) {
                adapter.submitList(t)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}