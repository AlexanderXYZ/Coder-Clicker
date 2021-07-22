package com.buslaev.coderclicker.adapters

import android.util.Log
import com.buslaev.coderclicker.ClickerApplication
import com.buslaev.coderclicker.ClickerApplication.Companion.globalClickCode
import com.buslaev.coderclicker.ClickerApplication.Companion.globalCodesPerClick
import com.buslaev.coderclicker.ClickerApplication.Companion.globalComponentsShop
import com.buslaev.coderclicker.ClickerApplication.Companion.globalIncomeShop
import com.buslaev.coderclicker.ClickerApplication.Companion.globalLanguagesShop
import com.buslaev.coderclicker.ClickerApplication.Companion.globalMoney
import com.buslaev.coderclicker.ClickerApplication.Companion.globalProgramsShop
import com.buslaev.coderclicker.models.ShopModel
import com.buslaev.coderclicker.other.Constants.MAX_REMAINED
import com.buslaev.coderclicker.other.Constants.MULTIPLIER
import com.buslaev.coderclicker.other.Shops
import com.buslaev.coderclicker.other.Shops.*
import kotlin.math.pow
import kotlin.math.roundToInt

class CurrentShopItem(
    private val currentItem: ShopModel,
    private val shop: Shops
) {
    private var changedPrice = ""
    private var changedRemained = currentItem.remained.toInt()

    fun getTitle(): String = currentItem.title
    fun getGrowth(): String = currentItem.growth
    fun getImageUrl(): String = currentItem.imageUrl
    fun getRemained(): String = changedRemained.toString()
    fun getPrice(): String {
        val oldPrice = currentItem.price.toInt()
        val remained = currentItem.remained.toInt()
        changedPrice = setChangedPrice(oldPrice, remained)
        return changedPrice
    }

    fun remainedEqualZero(): Boolean {
        return currentItem.remained.toInt() == 0
    }

    fun globalMoneyMorePriceItem(): Boolean {
        return globalMoney > changedPrice.toInt()
    }

    fun setGlobalVariables() {
        if (shop == INCOME) {
            globalClickCode -= changedPrice.toInt()
        } else {
            globalMoney -= changedPrice.toInt()
        }
        globalCodesPerClick += getGrowth().toInt()
        changedRemained = getRemained().toInt() - 1
        when (shop) {
            LANGUAGES -> globalLanguagesShop[currentItem.imageUrl] = changedRemained
            PROGRAMS -> globalProgramsShop[currentItem.imageUrl] = changedRemained
            COMPONENTS -> globalComponentsShop[currentItem.imageUrl] = changedRemained
            INCOME -> globalIncomeShop[currentItem.imageUrl] = changedRemained
        }
    }

    private fun setChangedPrice(oldPrice: Int, remained: Int): String {
        val multiplier = MULTIPLIER.pow(MAX_REMAINED - remained)
        val result = oldPrice * multiplier
        return result.roundToInt().toString()
    }
}