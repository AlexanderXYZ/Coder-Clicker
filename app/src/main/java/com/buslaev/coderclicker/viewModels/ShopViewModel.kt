package com.buslaev.coderclicker.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buslaev.coderclicker.data.repository.Repository
import com.buslaev.coderclicker.models.ShopModel
import com.buslaev.coderclicker.other.Constants.LANGUAGE_TYPE
import kotlinx.coroutines.launch

class ShopViewModel(shopType: String) : ViewModel() {

    private val repository = Repository()

    private val _languagesData = MutableLiveData<List<ShopModel>>()
    val languagesData: LiveData<List<ShopModel>> get() = _languagesData

    init {
        when (shopType) {
            LANGUAGE_TYPE -> getLanguages()
        }
    }

    private fun getLanguages() = viewModelScope.launch {
        val data = repository.getLanguages()
        _languagesData.postValue(data)
    }
}