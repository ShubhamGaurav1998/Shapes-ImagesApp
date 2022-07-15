package retrofit

import com.example.shapesapp.models.ImageApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ImageService {

    @GET("{path}")
    suspend fun getResults(@Path("path") path : String,
                           @Query("per_page") perPage: String,
                           @Query("client_id") clientId: String) :Response<ImageApiResponse>
}
