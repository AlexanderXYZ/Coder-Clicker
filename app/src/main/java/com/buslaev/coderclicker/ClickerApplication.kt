package com.buslaev.coderclicker

import android.app.Application

class ClickerApplication : Application() {

    companion object {
        var globalClickCode: Int = 0
        var globalMoney: Int = 0
        var globalCodesPerClick: Int = 1
        var globalMoneyPerSecond: Int = 0

        var globalLanguagesShop: HashMap<String, Int> = hashMapOf()
        var globalProgramsShop: HashMap<String, Int> = hashMapOf()
        var globalComponentsShop: HashMap<String, Int> = hashMapOf()
        var globalIncomeShop: HashMap<String, Int> = hashMapOf()
    }

}