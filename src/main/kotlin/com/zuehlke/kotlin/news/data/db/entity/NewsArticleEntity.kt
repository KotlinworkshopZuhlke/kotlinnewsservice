package com.zuehlke.kotlin.news.data.db.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
class NewsArticleEntity (
    @ManyToOne
    var source: ArticleSourceEntity? = null,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: LocalDateTime? = null,
    var content: String? = null,

    @Id
    @GeneratedValue
    var id: Long? = null
)

@Entity
class ArticleSourceEntity (
    var name: String? = null,

    @Id
    @GeneratedValue
    var id: Long? = null,
)