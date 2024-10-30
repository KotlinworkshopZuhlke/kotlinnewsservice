package com.zuehlke.kotlin.news

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient

@SpringBootApplication
class NewsApplication {
    @Value("\${newsapi.baseUrl}")
    lateinit var newsApiBaseUrl: String

    @Bean
    fun newsWebClient(): WebClient {
        return WebClient.builder().baseUrl(newsApiBaseUrl).build()
    }

}

fun main(args: Array<String>) {
    runApplication<NewsApplication>(*args)
}
