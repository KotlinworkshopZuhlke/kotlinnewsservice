package com.zuehlke.kotlin.news.domain.htmldsl

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test


class HtmlRenderingTest {

    @Test
    fun `Test1 Given simple Head DSL, then correct html is created`() {
        val headAndTitle = Head().title { +"Kotlin Course" }.toString()

        headAndTitle shouldBe "<title>\n" +
                "   Kotlin Course\n" +
                "</title>\n"
    }

    @Test
    fun `Test2 Given simple Html, Head, Title DSL, then correct html is created`() {
        val htmlHeadTitle = html {
            head {
                title { +"Kotlin Course" }
            }
        }

        htmlHeadTitle.toString() shouldBe "<html>\n" +
                "   <head>\n" +
                "      <title>\n" +
                "         Kotlin Course\n" +
                "      </title>\n" +
                "   </head>\n" +
                "</html>\n"
    }
}