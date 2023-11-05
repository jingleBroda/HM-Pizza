package com.example.presentation.fragment.mainMenuFragment.adapter.pizzaRangeAdapter.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.example.presentation.fragment.mainMenuFragment.adapter.pizzaRangeAdapter.PizzaRangeData

class PizzaRangeDiffUtils(
    private val oldList:List<PizzaRangeData>,
    private val newList:List<PizzaRangeData>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        when {
            oldList[oldItemPosition].nameCategory != newList[newItemPosition].nameCategory ->{
                false
            }

            oldList[oldItemPosition].activeStatus != newList[newItemPosition].activeStatus ->{
                false
            }

            else -> true
        }
}