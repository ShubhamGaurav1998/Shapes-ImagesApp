package com.example.shapesapp.dataSource

import android.content.Context
import androidx.paging.PageKeyedDataSource
import com.example.shapesapp.models.ImageApiResponse
import com.example.shapesapp.models.ImageApiResponseItem
import com.example.shapesapp.retrofit.ImageService
import com.example.shapesapp.retrofit.RetrofitInstance
import com.example.shapesapp.utils.Constants
import com.example.shapesapp.utils.Utility.isInternetAvailable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageDataSource(private val context: Context) : PageKeyedDataSource<Int, ImageApiResponseItem>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ImageApiResponseItem>) {
        if (context.isInternetAvailable()) {
                getImages(callback) }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ImageApiResponseItem>) {
        if (context.isInternetAvailable()) {
            val nextPageNo = params.key + 1
            getMoreImages(params.key, nextPageNo, callback)}
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ImageApiResponseItem>) {
        if (context.isInternetAvailable()) {
            val previousPageNo = if (params.key > 1) params.key - 1 else 0
            getMoreImages(params.key, previousPageNo, callback)
        }
    }

    private fun getImages(callback: LoadInitialCallback<Int, ImageApiResponseItem>) {

        RetrofitInstance.getRetrofitInstance().create(ImageService::class.java)
            .getResults(Constants.IMAGES_API_PATH, 1, Constants.PAGE_SIZE, Constants.CLIENT_ID).enqueue(object : Callback<ImageApiResponse> {
                override fun onFailure(call: Call<ImageApiResponse>, t: Throwable) {
                }

                override fun onResponse(call: Call<ImageApiResponse>, response: Response<ImageApiResponse>) {
                    val imagesResponse = response.body()
                    imagesResponse?.let { callback.onResult(it, null, 2) }
                    }

            })

    }

    private fun getMoreImages(pageNo: Int, previousOrNextPageNo: Int, callback: LoadCallback<Int, ImageApiResponseItem>) {

        RetrofitInstance.getRetrofitInstance().create(ImageService::class.java)
            .getResults(Constants.IMAGES_API_PATH, pageNo, Constants.PAGE_SIZE, Constants.CLIENT_ID).enqueue(object : Callback<ImageApiResponse>  {
            override fun onFailure(call: Call<ImageApiResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<ImageApiResponse>, response: Response<ImageApiResponse>) {
                val imagesResponse = response.body()
                imagesResponse?.let { callback.onResult(it, previousOrNextPageNo) }
            }

        })

    }

}