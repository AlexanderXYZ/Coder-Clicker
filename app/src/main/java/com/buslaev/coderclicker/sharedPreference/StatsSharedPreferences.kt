package com.buslaev.coderclicker.sharedPreference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.buslaev.coderclicker.ClickerApplication.Companion.globalClickCode
import com.buslaev.coderclicker.ClickerApplication.Companion.globalCodesPerClick
import com.buslaev.coderclicker.ClickerApplication.Companion.globalMoney
import com.buslaev.coderclicker.other.Constants.APP_CODES_PER_CLICK
import com.buslaev.coderclicker.other.Constants.APP_COUNTS_OF_CODE
import com.buslaev.coderclicker.other.Constants.APP_COUNTS_OF_MONEY
import com.buslaev.coderclicker.other.Constants.STATS_STORAGE

class StatsSharedPreferences(context: Context) : CoderSharedPreferences {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(STATS_STORAGE, MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    override fun putData() {
        editor.apply {
            putInt(APP_COUNTS_OF_CODE, globalClickCode)
            putInt(APP_COUNTS_OF_MONEY, globalMoney)
            putInt(APP_CODES_PER_CLICK, globalCodesPerClick)
        }.apply()
    }

    override fun getData() {
        globalClickCode = sharedPreferences.getInt(APP_COUNTS_OF_CODE, 0)
        globalMoney = sharedPreferences.getInt(APP_COUNTS_OF_MONEY, 0)
        globalCodesPerClick = sharedPreferences.getInt(APP_CODES_PER_CLICK, 1)
    }

}