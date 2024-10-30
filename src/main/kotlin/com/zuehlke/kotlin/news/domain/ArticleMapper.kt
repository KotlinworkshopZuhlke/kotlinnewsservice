package com.zuehlke.kotlin.news.domain

import com.zuehlke.kotlin.news.data.db.entity.ArticleSourceEntity
import com.zuehlke.kotlin.news.data.db.entity.NewsArticleEntity
import com.zuehlke.kotlin.news.model.Article
import com.zuehlke.kotlin.news.model.ArticleSource

fun Article.mapToEntity() = NewsArticleEntity(
    title = this.title,
    author = this.author,
    source = this.source?.mapToEntity(),
    description = this.description,
    url = this.url,
    urlToImage = this.urlToImage,
    publishedAt = this.publishedAt,
    content = this.content
)

fun ArticleSource.mapToEntity() = ArticleSourceEntity(
    name = this.name
)

fun NewsArticleEntity.mapToModel() = Article(
    title = this.title,
    author = this.author,
    source = this.source?.mapToModel(),
    description = this.description,
    url = this.url,
    urlToImage = this.urlToImage,
    publishedAt = this.publishedAt,
    content = this.content
)

fun ArticleSourceEntity.mapToModel() = ArticleSource(
    id = this.id.toString(), name = this.name
)