package com.zuehlke.kotlin.news.data

import com.zuehlke.kotlin.news.data.db.NewsArticleRepository
import com.zuehlke.kotlin.news.data.service.NewsServiceRemote
import com.zuehlke.kotlin.news.domain.DataService
import com.zuehlke.kotlin.news.domain.mapToModel
import com.zuehlke.kotlin.news.model.NewsFeed
import org.springframework.stereotype.Service

@Service
class DataServiceImpl(
    private val newsServiceRemote: NewsServiceRemote,
    private val newsServiceLocal: NewsArticleRepository
) : DataService {
    override fun fetchNews(): Result<NewsFeed> {
        val newsFromDB = newsServiceLocal.findAllByOrderByPublishedAt().toList()
        return if (newsFromDB.isEmpty()) {
            newsServiceRemote.fetchNews()
        } else {
            val articles = newsFromDB.map {articleEntity ->
                articleEntity.mapToModel()
            }
            Result.success(
                NewsFeed(
                    status = "DB",
                    totalResults = newsFromDB.size,
                    articles = articles
                )
            )

        }
    }
}