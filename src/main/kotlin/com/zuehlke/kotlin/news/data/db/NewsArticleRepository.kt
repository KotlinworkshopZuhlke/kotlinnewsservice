package com.zuehlke.kotlin.news.data.db

import com.zuehlke.kotlin.news.data.db.entity.NewsArticleEntity
import org.springframework.data.repository.CrudRepository


interface NewsArticleRepository : CrudRepository<NewsArticleEntity, Long> {
    fun findNewsArticleEntityById(id: Long): NewsArticleEntity?
    fun findAllByOrderByPublishedAt(): Iterable<NewsArticleEntity>
    fun findNewsArticleEntityByUrl(url: String): NewsArticleEntity?
}