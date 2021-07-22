package com.buslaev.coderclicker.adapters

import com.buslaev.coderclicker.other.Constants.MILLION
import com.buslaev.coderclicker.other.Constants.THOUSAND

interface CurrentItem {

    fun getTitle(): String
    fun getGrowth(): String
    fun getImageUrl(): String
    fun getRemained(): String
    fun getPrice(): String
    fun remainedEqualZero(): Boolean
    fun setGlobalVariables()


    fun transformPrice(price: Int): String {
        return when (price) {
            in THOUSAND until MILLION -> {
                val a = price / 100 % 10
                if (a == 0) {
                    "${price / THOUSAND}K"
                } else {
                    "${price / THOUSAND}.${price / 100 % 10}K"
                }
            }
            else -> price.toString()
        }
    }
}