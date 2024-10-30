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

    /*
    TODO Step 5: handle the fetcheNews() Result success or failure with a "when" statement
     In case of Failure Reply with HTTP 404 NOT FOUND.
     */
    @GetMapping("api/news")
    fun fetchNews() = newsServiceRemote.fetchNews()

}