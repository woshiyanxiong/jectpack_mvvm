package com.yx.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.common.base.BasePageAdapter
import com.yx.news.ui.fragment.NewFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
//https://blog.csdn.net/qq243348167/article/details/103741986
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var titleList = arrayListOf<String>("新闻"
        , "娱乐"
//        , "体育", "财经", "军事", "科技", "手机", "数码", "时尚", "游戏"
    )
    var titleType = arrayListOf<String>(
        "T1348647853363",
        "T1348648517839"
//        "BA8E6OEOwangning",
//        "BA8EE5GMwangning",
//        "BAI67OGGwangning",
//        "BA8D4A3Rwangning",
//        "BAI6I0O5wangning",
//        "BAI6JOD9wangning",
//        "BA8F6ICNwangning",
//        "BAI6RHDKwangning"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView(){
        val mFragment=ArrayList<Fragment>()
        for (i in titleList.indices){
            mFragment.add(NewFragment().newInstance(titleType[i]))
        }
        val  adapter=BasePageAdapter(supportFragmentManager,mFragment,titleList)
        viewPager.adapter=adapter
        tabLyout.setupWithViewPager(viewPager)

    }
}
