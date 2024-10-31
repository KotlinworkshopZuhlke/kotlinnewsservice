package com.zuehlke.kotlin.news.data

import com.zuehlke.kotlin.news.data.db.NewsArticleRepository
import com.zuehlke.kotlin.news.data.service.NewsServiceRemote
import com.zuehlke.kotlin.news.domain.DataService
import com.zuehlke.kotlin.news.domain.mapToEntity
import com.zuehlke.kotlin.news.domain.mapToModel
import com.zuehlke.kotlin.news.model.NewsFeed
import org.springframework.stereotype.Service

@Service
class DataServiceImpl(
    private val newsServiceRemote: NewsServiceRemote,
    private val newsServiceLocal: NewsArticleRepository
) : DataService {
    override fun fetchNews(): Result<NewsFeed> {

        val newsFromRemote = newsServiceRemote.fetchNews()

        return if (newsFromRemote.isFailure) { // fetch them from the DB
            fetchNewsFromDB()
        } else {
            //save the result into the DB:
            val dbEntitiesToBeSaved = newsFromRemote.getOrNull()?.articles?.map { it.mapToEntity() }
            dbEntitiesToBeSaved?.let { newsServiceLocal.saveAll(it) }

            // return the Remote Result
            newsFromRemote
        }
    }

    override fun fetchNewsFromDB(): Result<NewsFeed> {
        val newsFromDB = newsServiceLocal.findAllByOrderByPublishedAt().toList()
        val articles = newsFromDB.map { articleEntity ->
            articleEntity.mapToModel()
        }
        return Result.success(
            NewsFeed(
                status = "DB",
                totalResults = newsFromDB.size,
                articles = articles
            )
        )
    }
}