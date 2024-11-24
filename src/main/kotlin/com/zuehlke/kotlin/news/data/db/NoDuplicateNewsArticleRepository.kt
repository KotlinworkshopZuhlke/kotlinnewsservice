package com.zuehlke.kotlin.news.data.db

import com.zuehlke.kotlin.news.data.db.entity.NewsArticleEntity
import com.zuehlke.kotlin.news.domain.mapToEntity

/**
 * TODO: STEP 1:
 *  Create a NoDuplicateNewsArticleRepository which has the same functionalities as the NewsArticleRepository,
 *  but the 'save(NewsArticleEntity)' should be adapted, such that the entity only gets saved, if no NewsArticleEntity
 *  with the same url already exists. (See DataServiceImpl)
 *  Use Kotlin's Class Delegation!!
 */

class NoDuplicateNewsArticleRepository (private val repository: NewsArticleRepository): NewsArticleRepository by repository {

    override fun <S : NewsArticleEntity?> save(entity: S & Any): S & Any {
        val articleEntity = findNewsArticleEntityByUrl(entity.url?: "")
        if (articleEntity == null) {
            return repository.save(entity)
        }
        return entity
    }

    override fun <S : NewsArticleEntity> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
        entities.forEach{
            save(it)
        }
        return entities
    }
}