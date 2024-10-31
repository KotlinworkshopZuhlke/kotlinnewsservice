package com.zuehlke.kotlin.news.domain

import com.zuehlke.kotlin.news.domain.htmldsl.html
import com.zuehlke.kotlin.news.model.Article
import org.springframework.stereotype.Service

@Service
class HtmlRenderServiceImpl : HtmlRenderService {
    override fun renderArticle(article: Article): String {

        return html {
            head {
                title { +"News Article from News Service!" }
            }
            body {
                h1 { +"${article.title}" }

                // an element with attributes and text content
                a(href = "${article.url}") { +"Link to Article" }

                // mixed content
                p {
                    +"${article.description}"
                    b { +" -${article.author}" }
                }
                img(src = "${article.urlToImage}") {}
            }
        }.toString()
    }
}