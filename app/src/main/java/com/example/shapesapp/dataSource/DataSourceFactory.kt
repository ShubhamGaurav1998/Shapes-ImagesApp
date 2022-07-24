package com.example.shapesapp.dataSource

import android.content.Context
import androidx.paging.DataSource
import com.example.shapesapp.models.ImageApiResponseItem


class DataSourceFactory(private val context: Context) : DataSource.Factory<Int, ImageApiResponseItem>() {

    override fun create(): DataSource<Int, ImageApiResponseItem> {
        val imageDataSource = ImageDataSource(context)
        return imageDataSource
    }

}
