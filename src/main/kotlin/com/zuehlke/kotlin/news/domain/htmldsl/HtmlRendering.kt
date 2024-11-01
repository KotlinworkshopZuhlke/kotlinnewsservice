package com.zuehlke.kotlin.news.domain.htmldsl

import org.w3c.dom.Text

/*
TODO: Goal:
 In this exercise you will implement your own HTML-Kotlin type-safe DSL language, which will allow you to generate
 HTML with your own Kotlin-DSL:
 Now we will extend our previously created DSL to also be able to add more elements to the DSL
"""
            html {
//            head {
//                title { +"News Article from News Service!" }
//            }
//            body {
//                h1 { +"${article.title}" }
//
//                // an element with attributes and text content
//                a(href = "${article.url}") { +"Link to Article" }
//
//                // mixed content
//                p {
//                    +"${article.description}"
//                    b { +" -${article.author}" }
//                }
//                img(src = "${article.urlToImage}") {}
//            }
//        }
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


// TODO Step 1 It should be forbidden to do the following
/*
html {
    head {
        head {} // should be forbidden
    }
    // ...
}
 */
// TODO Kotlin provides the '@DslMarker' to have the compiler control the scopes. An annotation with '@DslMarker' can
//  annotate any Builder, which you want to control.
//@DslMarker
//annotation class HtmlTagMarker

//TODO Step 2: Apply the created annotation to the Tag, since all our Builder inherit from 'Tag',
// the above annotation can be used.
//@HtmlTagMarker
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

// TODO Step 3: Create an abstract class BodyTag which extends TagWithText.
//  Leave it empty for now, we will add the functions to it in the next step.

/*
 TODO Step 4: Create a class Body that extends BodyTag("body")
  Similarly to the head inside the HTML, add also a function called 'body' inside the HTML class.
 */

/*
 TODO Step 5: Create a class B that extends BodyTag("b")
  Add a function 'b' to the abstract BodyTag class, which takes an 'init' parameter of type B.()->Unit. As with 'head'-
  function inside the HTML, it will call the initTag with a new instance of B and the 'init' from the function parameter.
 */

/*
TODO Step 6: Repeat this for P "p", and h1 "h1"
 */

/*
TODO Step 7: Now we want to create the Link Tag: <a href="some link"></a>
 Ass you see this tag has an argument 'href'
 Create a class A that extends BodyTag("a"). It should have a mutable property 'href' of type String.
 Use property delegation
  The getter should retrieve attributes["href]
  The setter should assign the value to attributes["href"]
 */

/*
TODO Step 8: Add the function called 'a' to the BodyTag class. Different than the other functions there, 'a' will have
 an additional first function parameter 'href' which is simply String. The second argument will be the usual 'init' lambda
  Inside the implementation you should not only call initTag with A(), but then also set the href value on the 'initTag'
   returned Tag!

   That's it: go to the HtmlRenderServiceImpl, run the Spring Boot app and see the first news saved.
   // first call "/api/news", to save the news in the DB.  Then call "ui/article" to see an article from the DB
   rendered with your HTML-DSL :)
 */


class HTML : TagWithText("html") {

    fun head(init: Head.() -> Unit) = initTag(Head(), init)
}

fun html(init: HTML.() -> Unit): HTML {
    val root = HTML()
    root.init()
    return root
}
