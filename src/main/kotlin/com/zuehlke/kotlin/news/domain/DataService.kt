package com.zuehlke.kotlin.news.domain

import com.zuehlke.kotlin.news.model.NewsFeed

interface DataService {

    fun fetchNews(): Result<NewsFeed>

    fun fetchNewsFromDB(): Result<NewsFeed>
}