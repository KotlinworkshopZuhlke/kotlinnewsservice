package com.zuehlke.kotlin.news.model

/**
 * Bonus question of Exercise 3
 */
sealed class FetchResult<T> {

    data class Success<T>(val result: T) : FetchResult<T>()

    data object Failure : FetchResult<Nothing>()
}

sealed class FetchResultWithException<T> {

    data class Success<T>(val result: T) : FetchResultWithException<T>()

    data class Failure(val exception: Exception) : FetchResultWithException<Nothing>()
}