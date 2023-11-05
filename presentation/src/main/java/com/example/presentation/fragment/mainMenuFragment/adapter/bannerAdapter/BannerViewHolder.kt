package com.example.presentation.fragment.mainMenuFragment.adapter.bannerAdapter

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.BannerItemBinding
import com.example.presentation.utils.BaseViewHolder

class BannerViewHolder(
    private val item: BannerItemBinding
): BaseViewHolder<@receiver:DrawableRes Int>(item) {

    override fun bind(data: Int) {
        with(item) {
            bannerImg.setImageResource(data)
        }
    }
}