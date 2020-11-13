package com.example.focusstart8.data.network

import com.example.focusstart8.data.Constants
import com.example.focusstart8.data.models.ImageUploadResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImgurApiService {
    @Multipart
    @POST("upload")
    fun postImage(
        @Part("title") title: String,
        @Part("description") description: String,
        @Part("type") type: String,
        @Part file: MultipartBody.Part
    ): Single<ImageUploadResponse>

    companion object {

        fun create(): ImgurApiService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(AuthorizationInterceptor())
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ImgurApiService::class.java)
        }
    }
}