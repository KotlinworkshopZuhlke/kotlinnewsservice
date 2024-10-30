package com.zuehlke.kotlin.news.data

import com.zuehlke.kotlin.news.data.service.NewsServiceRemote
import com.zuehlke.kotlin.news.domain.NewsRepository
import org.springframework.stereotype.Service

@Service
class NewsRepositoryImpl(private val newsServiceRemote: NewsServiceRemote): NewsRepository {
    override fun fetchNews() = newsServiceRemote.fetchNews()
}