package com.buslaev.coderclicker.viewModels.mainViewModel

import android.view.animation.AnimationUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buslaev.coderclicker.ClickerApplication.Companion.globalClickCode
import com.buslaev.coderclicker.ClickerApplication.Companion.globalCodesPerClick
import com.buslaev.coderclicker.ClickerApplication.Companion.globalMoney
import com.buslaev.coderclicker.R
import com.buslaev.coderclicker.other.Constants.BILLION
import com.buslaev.coderclicker.other.Constants.MILLION
import com.buslaev.coderclicker.other.Constants.THOUSAND

class MainViewModel : ViewModel() {

    private var _codes = MutableLiveData<String>()
    val codes: LiveData<String> get() = _codes

    private var _money = MutableLiveData<String>()
    val money: LiveData<String> get() = _money

    init {
        setCodesAndMoney()
    }

    fun setCodesAndMoney() {
        _codes.value = transformCounter(globalClickCode)
        _money.value = transformCounter(globalMoney)
    }

    fun increaseCounter() {
        globalClickCode += globalCodesPerClick
        val result: String = transformCounter(globalClickCode)
        _codes.value = result
    }

    private fun transformCounter(count: Int): String {
        return when (count) {
            in THOUSAND until MILLION -> {
//                val afterPoint = count / 10 % 100
//                val afterPointString = if (afterPoint < 10) "0$afterPoint" else afterPoint
                val countString = count.toString()
                val countLength = countString.length
                val substringAfterPoint = countString.substring(countLength - 3, countLength - 1)
                "${count / THOUSAND}.${substringAfterPoint}K"
            }
            in MILLION until BILLION -> {
                "${count / MILLION}.${count / 10_000 % 100}M"
            }
            else -> count.toString()
        }
    }
}