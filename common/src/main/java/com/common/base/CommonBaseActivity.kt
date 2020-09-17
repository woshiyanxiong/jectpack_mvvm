package com.common.base

import android.app.Fragment
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import java.util.*
import javax.inject.Inject

/**
 * create by 2020/5/23
 *
 * @author yx
 */
abstract class CommonBaseActivity<VB: ViewDataBinding>:AppCompatActivity(){
    lateinit var binding: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<VB>(this, getLayout())
        initView()
    }
    @LayoutRes
    protected abstract fun getLayout(): Int

    protected abstract fun initView()

    //设置toolbar
    fun setSupportToolBar(toolBar: Toolbar) {
        setSupportActionBar(toolBar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowHomeEnabled(true)
            actionBar.setHomeButtonEnabled(true)
        }
    }

    fun setTitle(title: String) {
        Objects.requireNonNull<ActionBar>(supportActionBar).setTitle(title)
    }

    override fun setTitle(title: Int) {
        Objects.requireNonNull<ActionBar>(supportActionBar).setTitle(getString(title))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}