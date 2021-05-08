package com.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 *create by 2021/5/8
 * 扩展函数一些常用的view扩展方法
 *@author yx
 */

fun Activity.navigationActivity(cl: Class<*>) {
    startActivity(Intent(this, cl))
}

fun Activity.navigationActivity(cl: Class<*>, bundle: Bundle) {
    startActivity(Intent(this, cl).putExtras(bundle))
}

fun Fragment.navigationActivity(cl: Class<*>, bundle: Bundle) {
    startActivity(Intent(activity, cl).putExtras(bundle))
}

fun Fragment.navigationActivity(cl: Class<*>) {
    activity?.startActivity(Intent(activity, cl))
}

/**
 * PopupWindow show或者hide 时窗口背景设置
 * @receiver PopupWindow
 * @param bgAlpha Float 透明度 0-1
 * @param mContext Context
 */
fun PopupWindow.setBackgroundAlpha(bgAlpha: Float, mContext: Context) {
    val lp = (mContext as Activity).window
        .attributes
    lp.alpha = bgAlpha
    mContext.window.attributes = lp
}

/**
 * 设置view的Margins距离
 * @receiver View
 * @param left Int
 * @param top Int
 * @param right Int
 * @param bottom Int
 */
fun View.setMarginsParam(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
    val lp = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
    lp.setMargins(left, top, right, bottom)
    layoutParams = lp
}

/**
 * 设置右图片
 * @receiver TextView
 * @param id Int 图片id
 */
fun TextView.setImageRight(id: Int) {
    if (id == 0) {
        setCompoundDrawables(null, null, null, null)
        return
    }
    val drawable: Drawable = resources.getDrawable(id)
    drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
    setCompoundDrawables(null, null, drawable, null)
}

/**
 * 设置上图片
 * @receiver TextView
 * @param id Int
 */
fun TextView.setImageTop(id: Int) {
    val drawable: Drawable = resources.getDrawable(id)
    drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
    setCompoundDrawables(null, drawable, null, null)
}

/**
 * 设置左图片
 * @receiver TextView
 * @param id Int
 */
fun TextView.setImageLeft(id: Int) {
    if (id == 0) {
        setCompoundDrawables(null, null, null, null)
        return
    }
    val drawable: Drawable = resources.getDrawable(id)
    drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
    setCompoundDrawables(drawable, null, null, null)
}

/**
 * 设置底部图片
 * @receiver TextView
 * @param id Int
 */
fun TextView.setImageBottom(id: Int) {
    if (id == 0) {
        setCompoundDrawables(null, null, null, null)
        return
    }
    val drawable: Drawable = resources.getDrawable(id)
    drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
    setCompoundDrawables(null, null, null, drawable)
}

/**
 * 设置字体颜色
 * @receiver TextView
 * @param color Int 颜色id
 */
fun TextView.setColor(color: Int) {
    setTextColor(context.resources.getColor(color))
}

/**
 * 获取网络视频封面 第一帧
 * @receiver String?
 * @param imageView ImageView
 */
fun String?.imageUrl(imageView: ImageView) {
    val url = this

    try {
        Thread {
            //new出对象
            //new出对象
            val retriever = MediaMetadataRetriever()
            try {
                retriever.setDataSource(this)
                val bitmap =
                    retriever.getFrameAtTime(1000000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
                imageView.post {
                    imageView.setImageBitmap(bitmap)
                }
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {

                retriever.release()
            }

        }.start()
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
    }

}