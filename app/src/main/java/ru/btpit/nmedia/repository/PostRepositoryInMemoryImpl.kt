package ru.btpit.nmedia.repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositoryInMemoryImpl : PostRepository{
    private var nextId=1
    private var posts = listOf(
        Post(
            id = nextId++,
            author = "КД-Саунд - мастерская звукоаппаратуры",
            content = "Купили дом, завезли красивую мебель, приобрели навороченную плазму - а звука нет? Тогда вам стоит посетить КД-Саунд в Борисоглебске! Мы делаем Hi-Fi оборудование на заказ на территории Борисоглебска, возможна доставка по области",
            published = "25 марта в 13:01",
            likedByMe = false,
            likes = 999999,
            shares = 999,
            shareByMe = false
        ),
        Post(
            id = nextId++,
            author = "КД-Саунд - мастерская звукоаппаратуры",
            content = "Дамы и господа, колонки ЗАКОНЧИЛИСЬ. Продажи закрыты.",
            published = "01 марта в 3:01",
            likedByMe = false,
            likes = 1247,
            shares = 100,
            shareByMe = false
        ),
    ).reversed()
    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data
    override fun save(post: Post) {
        if(post.id==0){
            posts = listOf(post.copy(
                id = nextId++,
                author = "Я",
                likedByMe = false,
                published = "Сейчас",
                shareByMe = false
            )
            ) + posts
            data.value = posts
            return
        }
        posts = posts.map{
            if (it.id != post.id) it else it.copy (content = post.content, likes = post.likes, shares = post.shares)
        }
        data.value = posts
    }
    override fun likeById(id: Int) {
        posts = posts.map {
            if (it.id != id) it else
                it.copy(likedByMe = !it.likedByMe, likes = if (!it.likedByMe) it.likes+1 else it.likes-1)
        }
        data.value = posts
    }
    override fun shareById(id: Int) {
        posts = posts.map {
            if (it.id != id) it else
                it.copy(shareByMe = !it.shareByMe, shares = it.shares+1)
        }
        data.value = posts
    }

    override fun removeById(id: Int) {
        posts = posts.filter { it.id!=id }
        data.value = posts
    }
    override fun postID(id: Int): LiveData<Post> {
        val postLiveData = MutableLiveData<Post>()
        postLiveData.value = posts.find { it.id == id }

        return postLiveData
    }


}

