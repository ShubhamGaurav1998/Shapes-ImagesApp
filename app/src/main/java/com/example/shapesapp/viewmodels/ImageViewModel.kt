package com.example.shapesapp.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.shapesapp.models.ImageApiResponseItem
import com.example.shapesapp.dataSource.DataSourceFactory
import com.example.shapesapp.dataSource.ImageDataSource

class ImageViewModel(private val context: Context) : ViewModel() {

    private var listUsers : LiveData<PagedList<ImageApiResponseItem>> = MutableLiveData<PagedList<ImageApiResponseItem>>()
    private var mutableLiveData = MutableLiveData<ImageDataSource>()

    init {
        val factory : DataSourceFactory by lazy {
            DataSourceFactory(context)
        }
        mutableLiveData = factory.mutableLiveData

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(6)
            .build()

        listUsers = LivePagedListBuilder(factory, config).build()

    }

    fun getData() : LiveData<PagedList<ImageApiResponseItem>>{
        return listUsers
    }


}