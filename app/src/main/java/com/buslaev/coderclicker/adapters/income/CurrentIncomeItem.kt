package com.buslaev.coderclicker.adapters.income

import android.util.Log
import com.buslaev.coderclicker.ClickerApplication.Companion.globalClickCode
import com.buslaev.coderclicker.ClickerApplication.Companion.globalIncomeShop
import com.buslaev.coderclicker.ClickerApplication.Companion.globalMoney
import com.buslaev.coderclicker.ClickerApplication.Companion.globalMoneyPerSecond
import com.buslaev.coderclicker.adapters.CurrentItem
import com.buslaev.coderclicker.models.ShopModel
import com.buslaev.coderclicker.other.Constants
import com.buslaev.coderclicker.other.Income
import com.buslaev.coderclicker.other.Income.PROJECTS
import com.buslaev.coderclicker.other.Income.SELLING
import com.buslaev.coderclicker.other.Shops
import kotlin.math.pow
import kotlin.math.roundToInt

class CurrentIncomeItem(
    private val currentItem: ShopModel
) : CurrentItem {

    private var changedPrice = ""
    private var changedRemained: Int
        get() {
            val remained = currentItem.remained
            return if (remained == "") {
                1
            } else remained.toInt()
        }
        set(value) {}

    private var income: Income = if (currentItem.projects) PROJECTS else SELLING

    override fun getTitle(): String = currentItem.title
    override fun getGrowth(): String {
        return if (income==SELLING){
            "+${currentItem.growth}$"
        } else {
            "+${currentItem.growth}$ per second"
        }
    }
    override fun getImageUrl(): String = currentItem.imageUrl
    override fun getRemained(): String =
        if (income == SELLING) "inf" else changedRemained.toString()

    override fun getPrice(): String {
        return if (income == SELLING) {
            changedPrice = currentItem.price
            transformPrice(currentItem.price.toInt())
        } else {
            val oldPrice = currentItem.price.toInt()
            val remained = currentItem.remained.toInt()
            changedPrice = setChangedPrice(oldPrice, remained)
            transformPrice(changedPrice.toInt())
        }
    }

    override fun remainedEqualZero(): Boolean {
        return if (income == SELLING) {
            false
        } else {
            currentItem.remained.toInt() == 0
        }
    }

    override fun setGlobalVariables() {
        globalClickCode -= changedPrice.toInt()
        if (income == SELLING) {
            globalMoney += getGrowth().toInt()
        } else {
            globalMoneyPerSecond += getGrowth().toInt()
            changedRemained = getRemained().toInt() - 1
            globalIncomeShop[getImageUrl()] = changedRemained
        }
    }

    fun globalClickMorePrice(): Boolean {
        return globalClickCode > changedPrice.toInt()
    }

    private fun setChangedPrice(oldPrice: Int, remained: Int): String {
        val multiplier = Constants.MULTIPLIER.pow(Constants.MAX_REMAINED - remained)
        val result = oldPrice * multiplier
        return result.roundToInt().toString()
    }
}