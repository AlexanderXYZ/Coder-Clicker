package com.buslaev.coderclicker.viewModels.shopViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShopViewModelFactory(private val shopType: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShopViewModel(shopType) as T
    }
}