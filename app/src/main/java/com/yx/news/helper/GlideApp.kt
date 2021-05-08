package com.yx.news.helper

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.yx.news.R

object GlideApp {
    /*
     *加载图片(默认)
     */
    fun loadImage(url: String?, imageView: ImageView) {
        val options =
            RequestOptions().centerCrop().placeholder(R.mipmap.ic_logo) //占位图
                .error(R.mipmap.ic_logo) //错误图
                // .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .fitCenter()
        Glide.with(imageView.context.applicationContext).load(url).apply(options)
            .into(imageView)
    }

    fun loadImage(
        context: Context?,
        url: String?,
        imageView: ImageView?
    ) {
        val options =
            RequestOptions().centerCrop().placeholder(R.mipmap.ic_logo) //占位图
                .error(R.mipmap.ic_logo) //错误图
                // .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .fitCenter()
        Glide.with(context!!).load(url).apply(options).into(imageView!!)
    }

    fun loadImage(
        context: Context?,
        url: Bitmap?,
        imageView: ImageView?
    ) {
        val options =
            RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher) //占位图
                .error(R.drawable.ic_launcher_background) //错误图
                // .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
        Glide.with(context!!).load(url).apply(options).into(imageView!!)
    }

    fun loadImage(
        context: Context?,
        url: Int,
        imageView: ImageView?
    ) {
        val options =
            RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher) //占位图
                .error(R.drawable.ic_launcher_background) //错误图
                // .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
        Glide.with(context!!).load(url).apply(options).into(imageView!!)
    }


    /**
     *
     * @param imageView ImageView
     * @param url String
     * @param round Int 圆角
     */
    fun loadImage(
        imageView: ImageView,
        url: String?,
        round: Int = 4,
        error: Int = R.color.colorAccent
    ) {
//        val options = RequestOptions.bitmapTransform(CenterCrop(imageView.context),RoundedCorners(round)).placeholder(error) //图片圆角为30
        val options = RequestOptions.bitmapTransform(RoundedCorners(round))
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .signature(ObjectKey(System.currentTimeMillis()))
            .skipMemoryCache(true)

        Glide.with(imageView.context).load(url) //图片地址
            .transform(CenterCrop(), GlideRoundTransform(round))
//                .apply(options)
            .placeholder(error)
            .into(imageView)
    }
}