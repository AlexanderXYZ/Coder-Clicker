package com.buslaev.coderclicker

import android.app.Application

class ClickerApplication : Application() {

    companion object {
        var globalClickCode: Int = 0
        var globalMoney: Int = 0
        var globalCodesPerClick: Int = 1

        var globalLanguagesShop: HashMap<String, Boolean> = hashMapOf()
        var globalProgramsShop: HashMap<String, Boolean> = hashMapOf()
        var globalComponentsShop: HashMap<String, Boolean> = hashMapOf()
        var globalIncomeShop: HashMap<String, Boolean> = hashMapOf()
    }

}