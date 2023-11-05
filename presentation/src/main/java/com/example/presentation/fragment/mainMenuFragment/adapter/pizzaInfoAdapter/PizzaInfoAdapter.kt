package com.example.presentation.fragment.mainMenuFragment.adapter.pizzaInfoAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Pizza
import com.example.presentation.databinding.PizzaCardBinding

class PizzaInfoAdapter(
    val pizzaInfoList: List<Pizza>
):RecyclerView.Adapter<PizzaInfoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaInfoViewHolder =
        PizzaInfoViewHolder(
            PizzaCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = pizzaInfoList.size

    override fun onBindViewHolder(holder: PizzaInfoViewHolder, position: Int) {
        holder.bind(pizzaInfoList[position])
    }
}