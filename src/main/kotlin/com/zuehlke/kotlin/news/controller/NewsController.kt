package com.zuehlke.kotlin.news.controller

import com.zuehlke.kotlin.news.data.service.NewsServiceRemote
import com.zuehlke.kotlin.news.model.Article
import com.zuehlke.kotlin.news.model.ArticleSource
import com.zuehlke.kotlin.news.model.NewsFeed
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NewsController(val newsServiceRemote: NewsServiceRemote) {

    @GetMapping("api/testnews")
    fun fetchTestNews() = NewsFeed(status = "", totalResults = 0, articles = listOf(
        Article(source = ArticleSource())
    ))

    @GetMapping("api/news")
    fun fetchNews() = newsServiceRemote.fetchNews()

}