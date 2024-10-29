package com.zuehlke.kotlin.news.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NewsController {

    @GetMapping("api/testnews")
    fun fetchTestNews() {}

}