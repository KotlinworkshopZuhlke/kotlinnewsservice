package com.zuehlke.kotlin.news.data

import com.zuehlke.kotlin.news.data.db.NewsArticleRepository
import com.zuehlke.kotlin.news.data.service.NewsServiceRemote
import com.zuehlke.kotlin.news.domain.DataService
import com.zuehlke.kotlin.news.domain.mapToEntity
import com.zuehlke.kotlin.news.domain.mapToModel
import com.zuehlke.kotlin.news.model.Article
import com.zuehlke.kotlin.news.model.NewsFeed
import org.springframework.stereotype.Service

@Service
class DataServiceImpl(
    private val newsServiceRemote: NewsServiceRemote,
    // TODO STEP: 2: replace with the newly created NoDuplicateNewsArticleRepository
    private val newsServiceLocal: NewsArticleRepository
) : DataService {
    override fun fetchNews(): Result<NewsFeed> {

        val newsFromRemote = newsServiceRemote.fetchNews()

        return if (newsFromRemote.isFailure) { // fetch them from the DB
            fetchNewsFromDB()
        } else {
            //save the result into the DB:
            val newsFeed = newsFromRemote.getOrNull()
            newsFeed?.let { saveNonExistingArticlesToDBIn(it) }

            // return the Remote Result
            newsFromRemote
        }
    }

    fun saveNonExistingArticlesToDBIn(newsFeed: NewsFeed) {
        for (article in newsFeed.articles) {
            article.url?.let {
                /*
                TODO: This check can be removed with the NoDuplicateNewsArticleRepository
                 */
                val articleEntity = newsServiceLocal.findNewsArticleEntityByUrl(article.url)
                if (articleEntity == null) {
                    newsServiceLocal.save(article.mapToEntity())
                }
            }
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

    override fun fetchFirstArticle(): Result<Article> {
        val articleFromDB = newsServiceLocal.findNewsArticleEntityById(1L)
        return if (articleFromDB == null) {
            Result.failure(RuntimeException("No article found"))
        } else {
            Result.success(articleFromDB.mapToModel())
        }
    }
}