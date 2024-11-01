package com.zuehlke.kotlin.news.domain.htmldsl

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
        //TODO Step 1: fill out the rendering function. Simply append to the builder the 'indent' and then the 'text'
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
        // TODO Step 2: Go through all attributes and create the string which will be added inside the TAG
        //  This function can be used in the subsequent render function
        //  "<TagName [renderAttributes()] > children </TagName>" or
        //  "<TagName [renderAttributes()] \>"
        TODO()
    }

    override fun render(builder: StringBuilder, indent: String) {
        // TODO Step 3: Append to the String Builder the following:
        //  [name] is the Tag-name create the whole string for the complete tag:
        //  indent<name renderAttributes()> [render all children] indent</name>
        // you can use the above function renderAttributes()
    }

    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "")
        return builder.toString()
    }
}


/*
TODO Step 4: Create an abstract class called 'TagWithText' which extends 'Tag'
 It should have a function which overloads the '+' operator for String,  (an extension function of String)
 Check: https://kotlinlang.org/docs/operator-overloading.html#unary-prefix-operators
 For extension functions: https://kotlinworkshopzuhlke.github.io/KotlinWorkshopPresentation/slides/basicsyntax/basicsyntax.html#/5/5
  the function overloading the "+" operator should add a TextElement(this) to the children. ("this" being the String it extends)
 */


/*
 TODO Step 5: Create class 'Title' with an empty constructor, which extends TagWithText("title").
  Since Title extends TagWithText, it already defines the function '+', which adds a TextElement to the children.
   Thats all we need for this class.
 */

/*
 TODO Step 6:
  To be able to write 'title{+"Kotlin Course"}' we need to define a function called 'title' that takes a lambda.
  </title> is an element that can only be inside a Head-Element
  Lets create a class 'Head', which extends TagWithText("head")
  it should define a function 'title' which has a lambda-argument 'init' of type Title.() -> Unit. the functions
   implementation should initialise the Tag. We already have defined a generic initTag function, so we can simply call
   that with a new Title and the passed 'init'.
   Remember: In Kotlin all functions have a return type. If there is nothing specified it will return 'Unit'.
  Like this we should be able to call title{+"Kotlin Course"} from a Head element.
 */


/*
TODO Step 7: Similarly to step 6, we want to be able to call
    """
    head{
        title{+"Kotlin Course"}
    }
    """  only inside an HTML Element.
 Create a class HTML which extends TagWithText("html"), which should have a head function with a similar implementation
  as in the previous step. It should initialise the 'Head'
*/

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

