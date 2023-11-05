package com.example.presentation.fragment.mainMenuFragment.adapter.pizzaRangeAdapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.databinding.PizzaRangeItemBinding
import com.example.presentation.fragment.mainMenuFragment.adapter.pizzaRangeAdapter.diffUtils.PizzaRangeDiffUtils

class PizzaRangeAdapter(
    pizzaRangeNameList: List<String>,
    private val onClick: View.OnClickListener
):RecyclerView.Adapter<PizzaRangeViewHolder>(), View.OnClickListener {
    private var pizzaRangeList = pizzaRangeNameList.map {
        if(it == pizzaRangeNameList.first()) PizzaRangeData(it, true)
        else PizzaRangeData(it, false)
    }

    private var currencyActiveCategory = pizzaRangeList.first().nameCategory

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaRangeViewHolder =
        PizzaRangeViewHolder(
            PizzaRangeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            this
        )

    override fun getItemCount(): Int = pizzaRangeList.size

    override fun onBindViewHolder(holder: PizzaRangeViewHolder, position: Int) =
        holder.bind(pizzaRangeList[position])

    private fun diffUtilActivate(newList:List<PizzaRangeData>, newActiveCategory:String) {
        val diffUtil = PizzaRangeDiffUtils(pizzaRangeList, newList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        pizzaRangeList = newList
        currencyActiveCategory = newActiveCategory
        diffResults.dispatchUpdatesTo(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.pizzaRangeLayout -> {
                val newActiveCategory = v.tag as String
                if(newActiveCategory != currencyActiveCategory) {
                    val newList = mutableListOf<PizzaRangeData>()
                    pizzaRangeList.forEach {
                        when(it.nameCategory) {
                            newActiveCategory -> newList.add(
                                PizzaRangeData(
                                    it.nameCategory,
                                    true
                                )
                            )

                            currencyActiveCategory -> newList.add(
                                PizzaRangeData(
                                    it.nameCategory,
                                    false
                                )
                            )

                            else -> newList.add(it.copy())
                        }
                    }
                    diffUtilActivate(newList, newActiveCategory)
                    onClick.onClick(v)
                }
            }
        }
    }
}