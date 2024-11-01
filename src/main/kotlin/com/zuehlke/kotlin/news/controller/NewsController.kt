package com.zuehlke.kotlin.news.controller

import com.zuehlke.kotlin.news.domain.DataService
import com.zuehlke.kotlin.news.domain.HtmlRenderService
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
class NewsController(
    val dataService: DataService,
    val htmlRenderService: HtmlRenderService
) {
    private val articles = mutableListOf(Article(source = ArticleSource()))


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

    @PostMapping("article")
    fun postArticle(
        @RequestBody article: Article?,
    ) {
        article?.let { articles.add(it) }
    }

    @GetMapping("articles")
    fun fetchArticles() = articles

    @GetMapping("api/news")
    fun fetchNews(): NewsFeed {
        val result = dataService.fetchNews()
        return result.getOrThrow()
    }

    @GetMapping("ui/article")
    fun renderArticle(): String {
        val article = dataService.fetchFirstArticle().getOrThrow()
        val htmlArticle = htmlRenderService.renderArticle(article)
        return htmlArticle.toString()
    }
}