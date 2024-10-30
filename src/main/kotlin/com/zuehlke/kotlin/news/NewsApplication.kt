package com.zuehlke.kotlin.news

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NewsApplication {
    /*
     TODO Step 2: Exercise 2 Config Properties: Inject the two properties inside [/resources/application.properties]
      with "@Value" from  Spring Boot. Name the properties of type String: "newsApiKey" and "newsBaseUrl"
      Difficulty: How can Spring Boot inject the correct values from the properties file,
      without you already providing a default init value?
      Continue with Step 3 in the build.gradle.kts file
     */

    /*
 	TODO Step 4: Exercise 2 Config Properties: Create a WebClient Bean, which uses the above Variables.
 	 Use Kotlin String interpolation to create the URL like this:
 	 newsBaseUrl + "?sources=bbc-news&apiKey=${apiKey}" + newsBaseUrl
 	 If you don't know how to do the String interpolation, then do it the Java-Way.
 	 The Compiler will give you a suggestion to auto-convert to the Kotlin-Way ;-):
 	 "String concatenation can be converted to a template"
 	 Hint: this is how you create the WebClient
 	 WebClient.builder().baseUrl( [concatenated-URL-String-from-above] ).build()
 	*/
}

fun main(args: Array<String>) {
    runApplication<NewsApplication>(*args)
}
