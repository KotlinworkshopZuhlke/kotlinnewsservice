package com.zuehlke.kotlin.news.domain

import com.zuehlke.kotlin.news.model.NewsFeed

interface NewsRepository {

    fun fetchNews(): NewsFeed
}