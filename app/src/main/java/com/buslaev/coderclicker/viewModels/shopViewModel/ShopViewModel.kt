package com.buslaev.coderclicker.viewModels.shopViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buslaev.coderclicker.data.repository.Repository
import com.buslaev.coderclicker.models.ShopModel
import com.buslaev.coderclicker.other.Components
import com.buslaev.coderclicker.other.Components.*
import com.buslaev.coderclicker.other.Constants.COMPONENTS_TYPE
import com.buslaev.coderclicker.other.Constants.INCOME_TYPE
import com.buslaev.coderclicker.other.Constants.LANGUAGE_TYPE
import com.buslaev.coderclicker.other.Constants.PROGRAMS_TYPE
import com.buslaev.coderclicker.other.Income
import com.buslaev.coderclicker.other.Income.*
import com.buslaev.coderclicker.other.Programs
import com.buslaev.coderclicker.other.Programs.*
import kotlinx.coroutines.launch

class ShopViewModel(shopType: String) : ViewModel() {

    private val repository = Repository()

    private val _languagesData = MutableLiveData<List<ShopModel>>()
    val languagesData: LiveData<List<ShopModel>> get() = _languagesData

    private val _programsData = MutableLiveData<List<ShopModel>>()
    val programsData: LiveData<List<ShopModel>> get() = _programsData

    private val _componentsData = MutableLiveData<List<ShopModel>>()
    val componentsData: LiveData<List<ShopModel>> get() = _componentsData

    private val _incomeData = MutableLiveData<List<ShopModel>>()
    val incomeData: LiveData<List<ShopModel>> get() = _incomeData

    init {
        when (shopType) {
            LANGUAGE_TYPE -> getLanguages()
            PROGRAMS_TYPE -> getPrograms(IDE)
            COMPONENTS_TYPE -> getComponents(PROCESSORS)
            INCOME_TYPE -> getIncome(SELLING)
        }
    }

    fun getPrograms(typeProgram: Programs) {
        getProgramsFromRepository(typeProgram)
    }

    fun getComponents(typeComponents: Components) {
        getComponentsFromRepository(typeComponents)
    }

    fun getIncome(typeIncome: Income) {
        getIncomeFromRepository(typeIncome)
    }

    private fun getLanguages() = viewModelScope.launch {
        val data = repository.getLanguages()
        _languagesData.postValue(data)
    }

    private fun getProgramsFromRepository(typeProgram: Programs) = viewModelScope.launch {
        val data = repository.getPrograms(typeProgram)
        _programsData.postValue(data)
    }

    private fun getComponentsFromRepository(typeComponents: Components) = viewModelScope.launch {
        val data = repository.getComponents(typeComponents)
        _componentsData.postValue(data)
    }

    private fun getIncomeFromRepository(typeIncome: Income) = viewModelScope.launch {
        val data = repository.getIncome(typeIncome)
        _incomeData.postValue(data)
    }
}