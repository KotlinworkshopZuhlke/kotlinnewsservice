package com.zuehlke.kotlin.news.controller

import com.zuehlke.kotlin.news.model.Article
import com.zuehlke.kotlin.news.model.ArticleSource
import com.zuehlke.kotlin.news.model.NewsFeed
import org.springframework.lang.NonNull
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/")
class NewsController {
    private val articles = mutableListOf(Article(source = ArticleSource()))

    @GetMapping("testnews")
    fun fetchTestNews() =
        NewsFeed(
            status = "",
            totalResults = 0,
            articles = articles,
        )

    @PostMapping("article")
    fun postArticle(
        @RequestBody article: Article?,
    ) {
        article?.let { articles.add(it) }
    }

    @GetMapping("articles")
    fun fetchArticles() = articles

}