package com.zuehlke.kotlin.news.controller

import com.zuehlke.kotlin.news.domain.DataService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class NewsControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var dataService: DataService

    @Test
    fun `when api_testnews is called then response is a Newsfeed with a default acticle`() {
        mockMvc.perform(get("/api/testnews")).andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    """
                     {
                      "status": "",
                      "totalResults": 0,
                      "articles": [
                        {
                          "source": {
                            "id": null,
                            "name": null
                          },
                          "author": null,
                          "title": null,
                          "description": null,
                          "url": null,
                          "urlToImage": null,
                          "publishedAt": null,
                          "content": null
                        }
                      ]
                    } 
                      """
                        .trimIndent()
                )
            )
    }
}