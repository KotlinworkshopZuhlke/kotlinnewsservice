package com.zuehlke.kotlin.news.data

import com.zuehlke.kotlin.news.data.service.NewsServiceRemote
import com.zuehlke.kotlin.news.domain.DataService
import org.springframework.stereotype.Service

@Service
class DataServiceImpl(private val newsServiceRemote: NewsServiceRemote): DataService {
    override fun fetchNews() = newsServiceRemote.fetchNews()
}