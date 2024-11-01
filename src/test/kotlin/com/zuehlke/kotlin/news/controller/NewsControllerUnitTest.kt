package com.zuehlke.kotlin.news.controller

import com.zuehlke.kotlin.news.domain.DataService
import com.zuehlke.kotlin.news.domain.HtmlRenderServiceImpl
import com.zuehlke.kotlin.news.model.Article
import com.zuehlke.kotlin.news.model.NewsFeed
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.mock.mockito.MockBean

class NewsControllerUnitTest : FeatureSpec(
    {

        feature("Post Article") {
            scenario("Post a valid article and fetch news") {
                val article = Article()

                val newsFeed = postArticleAndFetchNews(article)

                newsFeed shouldNotBe null
                newsFeed.articles shouldContain article
            }

            scenario("Post a invalid article and fetch news") {
                val article = null

                val newsFeed = postArticleAndFetchNews(article)

                newsFeed shouldNotBe null
                newsFeed.articles shouldNotContain article
            }
        }
    }
)

@MockBean
lateinit var newsServiceRemote: DataService

private fun postArticleAndFetchNews(article: Article?): NewsFeed {
    val newsController = NewsController(newsServiceRemote, HtmlRenderServiceImpl())
    newsController.postArticle(article)
    val newsFeed = newsController.fetchTestNews()
    return newsFeed
}