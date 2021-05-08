package com.yx.news.api

import com.yx.news.helper.async
import com.yx.news.model.NewResponse
import com.yx.news.model.NewResponses
import javax.inject.Inject

/**
 *create by 2020/6/19
 *@author yx
 */
class UserRepository @Inject internal constructor(private val apiService: BaseApiService) {
    /**
     * 协程请求
     */
    suspend fun getNews(type: String): NewResponses = apiService.getNews(type)

    /**
     * RxJava请求
     * @param type String
     * @return Single<(com.yx.news.model.NewResponses..com.yx.news.model.NewResponses?)>
     */
    fun getRxNews(type: String)=apiService.getRxNews(type).async()

}