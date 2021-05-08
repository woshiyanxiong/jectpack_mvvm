package com.yx.news.ui.adapter

import androidx.databinding.ObservableList
import com.common.base.BaseDataBingViewHolder
import com.common.base.BaseRecyclerViewAdapter
import com.yx.news.databinding.ItemNewBinding
import com.yx.news.helper.GlideApp
import com.yx.news.model.NewResponses.T1348647853363Bean

/**
 * create by 2020/6/19
 *
 * @author yx
 */
class NewAdapter(
    itemData: ObservableList<T1348647853363Bean>,
    layoutId: Int,
    brId: Int
) : BaseRecyclerViewAdapter<T1348647853363Bean, ItemNewBinding>(
    itemData,
    layoutId,
    brId
) {
    override fun bindViewHolder(
        viewHolder: BaseDataBingViewHolder<ItemNewBinding>,
        position: Int,
        t: T1348647853363Bean
    ) {
        super.bindViewHolder(viewHolder, position, t)
        viewHolder.binding.title.text = itemData[position]!!.title
        viewHolder.binding.source.text = itemData[position]!!.source
        GlideApp.loadImage(itemData[position]!!.imgsrc, viewHolder.binding.image)
    }

}