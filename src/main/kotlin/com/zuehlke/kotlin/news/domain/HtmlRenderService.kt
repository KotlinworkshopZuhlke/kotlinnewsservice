package com.zuehlke.kotlin.news.domain

import com.zuehlke.kotlin.news.model.Article

interface HtmlRenderService {

    fun renderArticle(article: Article): String
}