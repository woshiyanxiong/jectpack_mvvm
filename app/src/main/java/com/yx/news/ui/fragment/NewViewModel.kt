package com.yx.news.ui.fragment

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import com.common.base.subscribes
import com.common.viewmodel.BaseViewModel
import com.uber.autodispose.SingleSubscribeProxy
import com.yx.news.api.UserRepository
import com.yx.news.model.NewResponses
import javax.inject.Inject
import com.common.network.RequestObserver as RequestObserver1

/**
 * create by 2020/6/19
 *
 * @author yx
 */
class NewViewModel @Inject constructor(application: Application) : BaseViewModel(application) {
    @Inject
    lateinit var repository: UserRepository

    var itemNews: ObservableList<NewResponses.T1348647853363Bean> = ObservableArrayList()

    //协程请求->直接获取结果的
    fun getNews(type: String) {
        async({ repository.getNews(type) }
            , {
                itemNews.clear()
                itemNews.addAll(it.list)
            }, {
                it.printStackTrace()
            }, {

            })
    }

    //协程请求->带loading的
    fun getNewsLoading() {
        async({ repository.getNews("") }
            , {
                //返回结果
            }
            , true, {}, {})
    }

    //rxjava请求->
    fun getRxNews(type: String) {
        repository.getRxNews(type)
            .`as`(auto(this))
            .subscribes({
                itemNews.clear()
                itemNews.addAll(it.list)
            },{

            })
//            .subscribe({
//                itemNews.clear()
//                itemNews.addAll(it.list)
//            }, {
//                it.printStackTrace()
//            })
    }
}



