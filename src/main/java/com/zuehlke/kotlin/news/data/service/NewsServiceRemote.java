package com.zuehlke.kotlin.news.data.service;

import com.zuehlke.kotlin.news.model.NewsFeed;
import kotlin.Result;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClientRequest;

import java.time.Duration;

/*
TODO Step 1: Convert this java class to kotlin. Use IntelliJ's conversion function!
 Move the converted Kotlin class to the main/kotlin source-set (inside the same package structure)
 */

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
        // TODO Step 2: Can you handle nullability in Kotlin in a more concise way?
        if (newsFeed == null) throw new RuntimeException("NewsFeed could not be fetched");
        return newsFeed;

        /*
        TODO Step 3: Instead of throwing an Exception, it is preferable to return the pre-made "kotlin.Result" type.
         If the newsFeed is null return Result.failure otherwise Result.success containing the newsFeed
         */
    }
}
