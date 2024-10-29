package com.zuehlke.kotlin.news.model

import java.time.LocalDateTime

data class NewsFeed (
    val status: String? = null,
    val totalResults: Int? = null,
    val articles: List<Article> = listOf()
)

data class Article (
    val source: ArticleSource? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: LocalDateTime? = null,
    val content: String? = null
)

data class ArticleSource (
    val id: String? = null,
    val name: String? = null
)