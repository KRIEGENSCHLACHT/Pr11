package ru.btpit.nmedia.repository

    data class Post(
        val id: Int,
        val author: String,
        val content: String,
        val published: String,
        var likes: Int,
        var shares: Int,
        var likedByMe: Boolean,
        val shareByMe: Boolean
    )