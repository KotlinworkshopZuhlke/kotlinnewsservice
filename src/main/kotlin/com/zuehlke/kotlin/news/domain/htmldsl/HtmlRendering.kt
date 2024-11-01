package com.zuehlke.kotlin.news.domain.htmldsl

import org.w3c.dom.Text

/*
TODO: Goal:
 In this exercise you will implement your own HTML-Kotlin type-safe DSL language, which will allow you to generate
 HTML with your own Kotlin-DSL:
"""
         html {
            head {
                title { +"Kotlin Course" }
            }
        }
    """
 */
interface Element {
    fun render(builder: StringBuilder, indent: String)
}

class TextElement(val text: String) : Element {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append(indent + text + "\n")
    }
}

//@DslMarker
//annotation class HtmlTagMarker
//
//@HtmlTagMarker

/*
TODO: Fill in the gaps for an HTML Tag.
 Reminder- an HTML Tag looks like this:
 <TagName attribute="value">children</TagName>
 */
abstract class Tag(val name: String) : Element {
    val children = arrayListOf<Element>()
    val attributes = hashMapOf<String, String>()

    protected fun <T : Element> initTag(tag: T, init: T.() -> Unit): T {
        tag.init()
        children.add(tag)
        return tag
    }

    private fun renderAttributes(): String {
        val builder = StringBuilder()
        for ((key, value) in attributes) {
            builder.append(" $key=\"$value\"")
        }
        return builder.toString()
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.append(indent + "<$name${renderAttributes()}>" + "\n")
        for (child in children) {
            child.render(builder, "$indent   ")
        }
        builder.append("$indent</$name>\n")
    }

    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "")
        return builder.toString()
    }
}

abstract class TagWithText(name: String) : Tag(name) {
    operator fun String.unaryPlus() {
        children.add(TextElement(this))
    }
}

class Title : TagWithText("title")

class Head : TagWithText("head") {
    fun title(init: Title.() -> Unit) = initTag(Title(), init)
}

class HTML : TagWithText("html") {

    fun head(init: Head.() -> Unit) = initTag(Head(), init)
}

fun html(init: HTML.() -> Unit): HTML {
    val root = HTML()
    root.init()
    return root
}

/*
TODO Step 8: Create a function html which takes an argument called 'init' of type: HTML.() -> Unit and returns an HTML.
 The function should create a new HTML and call init on it and then return it.
 Great! Now you can already generate your first simple HTML
 Now you should be able to render your first HTML. Write
  """
  html {
    head {
        title{+"Kotlin Course"}
    }
  }

  Move to the next branch exercise/7_html_rendering_part2 for the rest of your DSL
 */

