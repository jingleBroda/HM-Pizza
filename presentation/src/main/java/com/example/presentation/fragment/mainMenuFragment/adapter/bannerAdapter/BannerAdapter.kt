package com.example.presentation.fragment.mainMenuFragment.adapter.bannerAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.BannerItemBinding

class BannerAdapter(
    private val adapterList: List<Banner>
):RecyclerView.Adapter<BannerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder =
        BannerViewHolder(BannerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = adapterList.size

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) =
        holder.bind(adapterList[position].imgRes)
}

@JvmInline
value class Banner(@DrawableRes val imgRes: Int)