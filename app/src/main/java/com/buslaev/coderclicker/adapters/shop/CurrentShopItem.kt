package com.buslaev.coderclicker.adapters.shop

import com.buslaev.coderclicker.ClickerApplication.Companion.globalClickCode
import com.buslaev.coderclicker.ClickerApplication.Companion.globalCodesPerClick
import com.buslaev.coderclicker.ClickerApplication.Companion.globalComponentsShop
import com.buslaev.coderclicker.ClickerApplication.Companion.globalIncomeShop
import com.buslaev.coderclicker.ClickerApplication.Companion.globalLanguagesShop
import com.buslaev.coderclicker.ClickerApplication.Companion.globalMoney
import com.buslaev.coderclicker.ClickerApplication.Companion.globalProgramsShop
import com.buslaev.coderclicker.adapters.CurrentItem
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
) : CurrentItem {
    private var changedPrice = ""
    private var changedRemained = currentItem.remained.toInt()

    override fun getTitle(): String = currentItem.title
    override fun getGrowth(): String {
        return "+${currentItem.growth} per click"
    }

    override fun getImageUrl(): String = currentItem.imageUrl
    override fun getRemained(): String = changedRemained.toString()
    override fun getPrice(): String {
        val oldPrice = currentItem.price.toInt()
        val remained = currentItem.remained.toInt()
        changedPrice = setChangedPrice(oldPrice, remained)
        return transformPrice(changedPrice.toInt())
    }

    override fun remainedEqualZero(): Boolean {
        return currentItem.remained.toInt() == 0
    }

    fun globalMoneyMorePriceItem(): Boolean {
        return globalMoney >= changedPrice.toInt()
    }

    override fun setGlobalVariables() {
        globalMoney -= changedPrice.toInt()
        globalCodesPerClick += currentItem.growth.toInt()
        changedRemained = getRemained().toInt() - 1
        when (shop) {
            LANGUAGES -> globalLanguagesShop[currentItem.imageUrl] = changedRemained
            PROGRAMS -> globalProgramsShop[currentItem.imageUrl] = changedRemained
            COMPONENTS -> globalComponentsShop[currentItem.imageUrl] = changedRemained
            else -> {
            }
        }
    }

    private fun setChangedPrice(oldPrice: Int, remained: Int): String {
        val multiplier = MULTIPLIER.pow(MAX_REMAINED - remained)
        val result = oldPrice * multiplier
        return result.roundToInt().toString()
    }
}