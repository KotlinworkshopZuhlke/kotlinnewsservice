package com.zuehlke.kotlin.news.controller

import com.zuehlke.kotlin.news.domain.DataService
import com.zuehlke.kotlin.news.model.Article
import com.zuehlke.kotlin.news.model.ArticleSource
import com.zuehlke.kotlin.news.model.NewsFeed
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NewsController(val dataService: DataService) {

    @GetMapping("api/testnews")
    fun fetchTestNews() = NewsFeed(
        status = "", totalResults = 0, articles = listOf(
            Article(source = ArticleSource())
        )
    )

    @GetMapping("api/dbnews")
    fun fetchDBTestNews(): NewsFeed {
        val result = dataService.fetchNewsFromDB()
        return result.getOrThrow()
    }

    @GetMapping("api/news")
    fun fetchNews(): NewsFeed {
        val result = dataService.fetchNews()
        return result.getOrThrow()
    }

}