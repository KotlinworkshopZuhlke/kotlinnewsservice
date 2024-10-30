package com.zuehlke.kotlin.news.data.service;

import com.zuehlke.kotlin.news.model.NewsFeed;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClientRequest;

import java.time.Duration;

@Service
public class NewsServiceRemote {
    public static final int REQUEST_TIMEOUT_IN_SECONDS = 5;
    private final WebClient webClient;

    public NewsServiceRemote(WebClient webClient) {
        this.webClient = webClient;
    }


    @NotNull
    public NewsFeed fetchNews() {
        NewsFeed newsFeed = webClient.get().httpRequest(httpRequest -> {
                    HttpClientRequest reactorRequest = httpRequest.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofSeconds(REQUEST_TIMEOUT_IN_SECONDS));
                })
                .retrieve()
                .bodyToMono(NewsFeed.class).block();
        if (newsFeed == null) throw new RuntimeException("NewsFeed could not be fetched");
        return newsFeed;
    }
}
