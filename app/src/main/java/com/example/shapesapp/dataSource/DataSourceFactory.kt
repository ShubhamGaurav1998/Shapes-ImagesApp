package com.example.shapesapp.dataSource

import com.example.shapesapp.models.ImageApiResponseItem
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource


class DataSourceFactory(private val context: Context) : DataSource.Factory<Int, ImageApiResponseItem>() {

    val mutableLiveData = MutableLiveData<ImageDataSource>()

    override fun create(): DataSource<Int, ImageApiResponseItem> {
        val imageDataSource = ImageDataSource(context)
        mutableLiveData.postValue(imageDataSource)
        return imageDataSource
    }

}
