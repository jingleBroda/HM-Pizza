package com.example.presentation.fragment.mainMenuFragment.adapter.pizzaRangeAdapter

import android.annotation.SuppressLint
import android.view.View
import com.example.presentation.R
import com.example.presentation.databinding.PizzaRangeItemBinding
import com.example.presentation.utils.BaseViewHolder

class PizzaRangeViewHolder(
    private val item: PizzaRangeItemBinding,
    private val onClick: View.OnClickListener,
): BaseViewHolder<PizzaRangeData>(item), View.OnClickListener {
    override fun bind(data: PizzaRangeData) {
        with(item) {
            if(data.activeStatus) {
                item.root.setBackgroundResource(R.drawable.pizza_range_active_background)
                pizzaRangeName.setTextColor(
                    item.root.context.resources.getColor(R.color.pizza_range_text_color_active,
                        null
                    )
                )
            }
            else {
                item.root.setBackgroundResource(R.drawable.pizza_range_background)
                pizzaRangeName.setTextColor(
                    item.root.context.resources.getColor(R.color.pizza_range_text_color_default,
                        null
                    )
                )
            }
            pizzaRangeName.text = data.nameCategory
            this.root.setOnClickListener(this@PizzaRangeViewHolder)
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.pizzaRangeLayout -> {
                v.tag = item.pizzaRangeName.text.toString()
                onClick.onClick(v)
            }
        }
    }
}

data class PizzaRangeData(
    val nameCategory: String,
    val activeStatus: Boolean
)