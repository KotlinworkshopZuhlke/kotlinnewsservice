package com.zuehlke.kotlin.news.data.db.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class NewsArticleEntity(
    @ManyToOne(cascade = [CascadeType.ALL])
    var source: ArticleSourceEntity? = null,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    @Column(unique = true)
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: LocalDateTime? = null,
    var content: String? = null,

    @Id
    @GeneratedValue
    var id: Long? = null
)

@Entity
class ArticleSourceEntity(
    var name: String? = null,

    @Id
    @GeneratedValue
    var id: Long? = null,
)