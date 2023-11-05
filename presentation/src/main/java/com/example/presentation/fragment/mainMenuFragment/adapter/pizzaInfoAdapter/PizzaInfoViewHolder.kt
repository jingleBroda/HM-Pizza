package com.example.presentation.fragment.mainMenuFragment.adapter.pizzaInfoAdapter

import android.content.Context
import android.graphics.Paint
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.domain.model.Pizza
import com.example.presentation.R
import com.example.presentation.databinding.PizzaCardBinding
import com.example.presentation.utils.BaseViewHolder

class PizzaInfoViewHolder(private val item:PizzaCardBinding): BaseViewHolder<Pizza>(item) {
    private val frameCreator = PizzaFrameCreator.Base(item.root.context)
    private val requestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    override fun bind(data: Pizza) {
        with(item.pizzaCardInclude) {
            pizzaImg.layoutParams = frameCreator.createFrameRect(pizzaImg)
            Glide.with(item.root.context)
                .load(data.imgUrl)
                .circleCrop()
                .placeholder(R.mipmap.project_icon)
                .error(R.mipmap.project_icon)
                .apply(requestOptions)
                .into(pizzaImg)

            pizzaRecipeLayout.run {
                namePizza.text = data.name

                compositionPizza.text = data.description
                compositionPizza.maxLines = 4
                compositionPizza.ellipsize = TextUtils.TruncateAt.END

                buyPizzaButton.text = this.root.context.getString(
                    R.string.buy_pizza_string,
                    data.price
                )
            }
        }
    }

    private abstract class PizzaFrameCreator(protected val contextFrameCreator: Context) {
        abstract fun createFrameRect(view: View): ViewGroup.LayoutParams

        class Base(
            context: Context
        ):PizzaFrameCreator(context){
            override fun createFrameRect(view: View): ViewGroup.LayoutParams {
                val displayMetrics = contextFrameCreator.resources.displayMetrics
                val boarderDp = 0F
                val boarderPx = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    boarderDp,
                    contextFrameCreator.resources.displayMetrics
                ).toInt()
                val widthPixels = displayMetrics.widthPixels
                val newWidthImg = ((widthPixels-boarderPx)/3)
                val layoutParams = view.layoutParams
                layoutParams.width = newWidthImg
                layoutParams.height = newWidthImg

                return layoutParams
            }
        }
    }
}