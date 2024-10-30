package com.zuehlke.kotlin.news

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient
import java.net.URI

@SpringBootApplication
class NewsApplication {
    @Value("\${newsapi.baseUrl}")
    lateinit var newsApiBaseUrl: String

    @Value("\${newsapi.apikey}")
    lateinit var newsApiKey: String

    @Bean
    fun newsWebClient(): WebClient {
        return WebClient.builder().baseUrl("${newsApiBaseUrl}?sources=bbc-news&apiKey=${newsApiKey}").build()
    }

}

fun main(args: Array<String>) {
    runApplication<NewsApplication>(*args)
}
