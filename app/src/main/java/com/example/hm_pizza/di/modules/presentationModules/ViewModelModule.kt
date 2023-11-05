package com.example.hm_pizza.di.modules.presentationModules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hm_pizza.di.modules.presentationModules.utils.ViewModelKey
import com.example.presentation.fragment.mainMenuFragment.MainMenuViewModel
import com.example.presentation.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainMenuViewModel::class)
    internal abstract fun bindMainMenuFragment(
        mainMenuViewModel:MainMenuViewModel
    ):ViewModel
}