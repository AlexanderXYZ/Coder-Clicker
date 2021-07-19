package com.buslaev.coderclicker.viewModels

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
            PROGRAMS_TYPE -> getProgramsIde()
            COMPONENTS_TYPE -> getComponentsProcessors()
            INCOME_TYPE -> getIncomeSelling()
        }
    }

    fun getPrograms(typeProgram: Programs) {
        when (typeProgram) {
            IDE -> getProgramsIde()
            OS -> getProgramsOs()
        }
    }

    fun getComponents(typeComponents: Components) {
        when (typeComponents) {
            PROCESSORS -> getComponentsProcessors()
            MOTHERBOARD -> getComponentsMotherboard()
            VIDEOCARDS -> getComponentsVideocards()
            MEMORY -> getComponentsMemory()
            MONITORS -> getComponentsMonitors()
            CORPUS -> getComponentsCorpus()
            KEYBOARDS -> getComponentsKeyboards()
            INTERNETS -> getComponentsInternets()
        }
    }

    fun getIncome(typeIncome: Income) {
        when (typeIncome) {
            SELLING -> getIncomeSelling()
            PROJECTS -> getIncomeProjects()
        }
    }

    /* languages */
    private fun getLanguages() = viewModelScope.launch {
        val data = repository.getLanguages()
        _languagesData.postValue(data)
    }

    /* programs */
    private fun getProgramsIde() = viewModelScope.launch {
        val data = repository.getProgramsIde()
        _programsData.postValue(data)
    }

    private fun getProgramsOs() = viewModelScope.launch {
        val data = repository.getProgramsIde()
        _programsData.postValue(data)
    }

    /* components */
    private fun getComponentsProcessors() = viewModelScope.launch {
        val data = repository.getComponents()
        _componentsData.postValue(data)
    }

    private fun getComponentsMotherboard() = viewModelScope.launch {
        val data = repository.getComponents()
        _componentsData.postValue(data)
    }

    private fun getComponentsVideocards() = viewModelScope.launch {
        val data = repository.getComponents()
        _componentsData.postValue(data)
    }

    private fun getComponentsMemory() = viewModelScope.launch {
        val data = repository.getComponents()
        _componentsData.postValue(data)
    }

    private fun getComponentsMonitors() = viewModelScope.launch {
        val data = repository.getComponents()
        _componentsData.postValue(data)
    }

    private fun getComponentsCorpus() = viewModelScope.launch {
        val data = repository.getComponents()
        _componentsData.postValue(data)
    }

    private fun getComponentsKeyboards() = viewModelScope.launch {
        val data = repository.getComponents()
        _componentsData.postValue(data)
    }

    private fun getComponentsInternets() = viewModelScope.launch {
        val data = repository.getComponents()
        _componentsData.postValue(data)
    }

    /* income */
    private fun getIncomeSelling() = viewModelScope.launch {
        val data = repository.getIncome()
        _incomeData.postValue(data)
    }

    private fun getIncomeProjects() = viewModelScope.launch {
        val data = repository.getIncome()
        _incomeData.postValue(data)
    }
}