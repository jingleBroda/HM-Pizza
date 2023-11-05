package com.example.hm_pizza.di.modules.presentationModules

import com.example.presentation.fragment.basketFragment.BasketFragment
import com.example.presentation.fragment.mainMenuFragment.MainMenuFragment
import com.example.presentation.fragment.profileFragment.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesMainMenuFragment(): MainMenuFragment
    @ContributesAndroidInjector
    abstract fun contributesBasketFragment(): BasketFragment
    @ContributesAndroidInjector
    abstract fun contributesProfileFragment(): ProfileFragment
}