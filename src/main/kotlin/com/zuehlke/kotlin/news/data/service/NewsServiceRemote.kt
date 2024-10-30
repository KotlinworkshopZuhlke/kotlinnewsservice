package com.zuehlke.kotlin.news.data.service

import com.zuehlke.kotlin.news.model.NewsFeed
import org.springframework.http.client.reactive.ClientHttpRequest
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClientRequest
import java.time.Duration

@Service
class NewsServiceRemote(private val webClient: WebClient) {
    fun fetchNews(): Result<NewsFeed> {
        val newsFeed = webClient.get().httpRequest { httpRequest: ClientHttpRequest ->
            val reactorRequest = httpRequest.getNativeRequest<HttpClientRequest>()
            reactorRequest.responseTimeout(Duration.ofSeconds(REQUEST_TIMEOUT_IN_SECONDS.toLong()))
        }
            .retrieve()
            .bodyToMono(NewsFeed::class.java).block()
        newsFeed?.let { return Result.success(it) }
        return Result.failure(RuntimeException("No News Feed Found"))
    }

    companion object {
        const val REQUEST_TIMEOUT_IN_SECONDS: Int = 5
    }
}
