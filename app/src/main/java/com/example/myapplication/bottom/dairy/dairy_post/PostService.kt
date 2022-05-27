package com.example.myapplication.bottom.dairy.dairy_post

import retrofit2.http.Body
import retrofit2.http.POST

interface PostService {

    @POST("post/")
    fun requestPost(
        @Body post: Post
    ): retrofit2.Call<PostState>
}