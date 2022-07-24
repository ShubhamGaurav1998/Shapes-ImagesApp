package com.example.shapesapp.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.shapesapp.dataSource.DataSourceFactory
import com.example.shapesapp.models.ImageApiResponseItem
import com.example.shapesapp.utils.Constants

class ImageViewModel(val context: Context) : ViewModel() {

    private var listImages : LiveData<PagedList<ImageApiResponseItem>>

    init {
        val factory : DataSourceFactory by lazy {
            DataSourceFactory(context)
        }
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(Constants.PAGE_SIZE)
            .build()

        listImages = LivePagedListBuilder(factory, config).build()

    }

    fun getData() : LiveData<PagedList<ImageApiResponseItem>>{
        return listImages
    }


}