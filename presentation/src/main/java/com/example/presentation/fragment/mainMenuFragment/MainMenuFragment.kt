package com.example.presentation.fragment.mainMenuFragment

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Pizza
import com.example.presentation.R
import com.example.presentation.contract.internetConnection
import com.example.presentation.databinding.FragmentMainMenuBinding
import com.example.presentation.fragment.mainMenuFragment.adapter.bannerAdapter.Banner
import com.example.presentation.fragment.mainMenuFragment.adapter.bannerAdapter.BannerAdapter
import com.example.presentation.fragment.mainMenuFragment.adapter.pizzaInfoAdapter.PizzaInfoAdapter
import com.example.presentation.fragment.mainMenuFragment.adapter.pizzaRangeAdapter.PizzaRangeAdapter
import com.example.presentation.fragment.mainMenuFragment.saveData.ListPizzaParcelize
import com.example.presentation.utils.GridSpacingItemDecoration
import com.example.presentation.utils.HorizontalSpaceItemDecoration
import com.example.presentation.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlin.properties.Delegates

class MainMenuFragment : DaggerFragment(R.layout.fragment_main_menu), OnClickListener {
    private lateinit var binding:FragmentMainMenuBinding
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var pizzaRangeAdapter: PizzaRangeAdapter
    private lateinit var pizzaInfoAdapter: PizzaInfoAdapter
    private var initialBannersHeight by Delegates.notNull<Int>()
    private var saveListPizza = listOf<Pizza>()
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<MainMenuViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        saveListPizza = if(Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU)
            savedInstanceState?.getParcelable(KEY_LIST_PIZZA, ListPizzaParcelize::class.java)?.pizzaListParcelizeToListPizza() ?: emptyList()
        else savedInstanceState?.getParcelable<ListPizzaParcelize>(KEY_LIST_PIZZA)?.pizzaListParcelizeToListPizza() ?: emptyList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMainMenuBinding.bind(view)
        with(binding) {
            navigationBar.run {
                val adapter = ArrayAdapter.createFromResource(
                    requireContext(),
                    R.array.City,
                    R.layout.city_spinner_item_dropdown
                )
                adapter.setDropDownViewResource(R.layout.city_spinner_item_dropdown)
                citySpinner.adapter = adapter
                dropdownImg.setOnClickListener { citySpinner.performClick() }
            }
            with(mainMenuCollapsingLayout){
                run {
                    bannerAdapter = BannerAdapter(
                        listOf(
                            Banner(R.drawable.banner2),
                            Banner(R.drawable.banner2),
                            Banner(R.drawable.banner2),
                        )
                    )
                    banners.layoutManager = LinearLayoutManager(
                        requireContext(),
                        RecyclerView.HORIZONTAL,
                        false
                    )
                    banners.adapter = bannerAdapter
                }
                run {
                    pizzaRangeAdapter = PizzaRangeAdapter(
                        requireContext().resources.getStringArray(R.array.PizzaRange).toList(),
                        this@MainMenuFragment
                    )
                    pizzaRange.layoutManager = LinearLayoutManager(
                        requireContext(),
                        RecyclerView.HORIZONTAL,
                        false
                    )
                    pizzaRange.addItemDecoration(HorizontalSpaceItemDecoration(20))
                    pizzaRange.adapter = pizzaRangeAdapter
                    pizzaRange.itemAnimator = null
                }
                run {
                    mainMenuScrolling.pizzaInfo.layoutManager = GridLayoutManager(requireContext(), 1)
                    mainMenuScrolling.pizzaInfo.addItemDecoration(GridSpacingItemDecoration())
                    initPizzaRecView()
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initPizzaRecView() {
        with(binding.mainMenuCollapsingLayout.mainMenuScrolling) {
            if(internetConnection().isInternetConnected()) {
                if(saveListPizza.isNotEmpty()) {
                    pizzaInfoAdapter = PizzaInfoAdapter(saveListPizza)
                    pizzaInfo.adapter = pizzaInfoAdapter
                    progressBar.visibility = View.GONE
                    emptyListMessage.visibility = View.GONE
                    pizzaInfo.visibility = View.VISIBLE
                }
                else {
                    viewModel.deleteRoomModel()
                    viewModel.getRetrofitPizza {
                        if(it.isEmpty()) {
                            progressBar.visibility = View.GONE
                            pizzaInfo.visibility = View.INVISIBLE
                            emptyListMessage.visibility = View.VISIBLE
                            emptyListMessage.text = requireContext().getString(
                                R.string.empty_list_message
                            )
                        }
                        else {
                            pizzaInfoAdapter = PizzaInfoAdapter(it)
                            pizzaInfo.adapter = pizzaInfoAdapter
                            progressBar.visibility = View.GONE
                            pizzaInfo.visibility = View.VISIBLE
                            emptyListMessage.visibility = View.GONE
                            viewModel.saveRoomPizza(it)
                        }
                    }
                }
            }
            else{
                viewModel.getRoomPizza { savePizzaList->
                    if(savePizzaList.isEmpty()) {
                        progressBar.visibility = View.GONE
                        pizzaInfo.visibility = View.INVISIBLE
                        emptyListMessage.visibility = View.VISIBLE
                        emptyListMessage.text = requireContext().getString(
                            R.string.empty_list_message
                        )
                    }
                    else {
                        pizzaInfoAdapter = PizzaInfoAdapter(savePizzaList)
                        pizzaInfo.adapter = pizzaInfoAdapter
                        progressBar.visibility = View.GONE
                        pizzaInfo.visibility = View.VISIBLE
                        emptyListMessage.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(KEY_LIST_PIZZA, ListPizzaParcelize.pizzaListToListPizzaParcelize(
            pizzaInfoAdapter.pizzaInfoList
        ))
        super.onSaveInstanceState(outState)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.pizzaRangeLayout -> {
                val category = v.tag as String
                val pizza =
                    requireContext().resources.getStringArray(R.array.PizzaRange).toList().first()
                if(category == pizza) {
                    with(binding.mainMenuCollapsingLayout.mainMenuScrolling) {
                        progressBar.visibility = View.VISIBLE
                        pizzaInfo.visibility = View.INVISIBLE
                        emptyListMessage.visibility = View.GONE
                    }
                    initPizzaRecView()
                }
                else{
                    with(binding.mainMenuCollapsingLayout.mainMenuScrolling) {
                        progressBar.visibility = View.GONE
                        pizzaInfo.visibility = View.INVISIBLE
                        emptyListMessage.visibility = View.VISIBLE
                        emptyListMessage.text = requireContext().getString(
                            R.string.products_coming_soon
                        )
                    }
                }
            }
        }
    }

    companion object {
        private const val KEY_LIST_PIZZA = "KEY_LIST_PIZZA"
    }
}