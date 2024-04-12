package ru.btpit.nmedia.repository

import androidx.lifecycle.LiveData

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Int)
    fun shareById(id: Int)
    fun postID(id: Int): LiveData<Post>
    fun removeById(id: Int)
    fun save(post: Post)


}