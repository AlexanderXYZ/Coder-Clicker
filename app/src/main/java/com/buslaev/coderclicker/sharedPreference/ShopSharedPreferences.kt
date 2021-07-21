package com.buslaev.coderclicker.sharedPreference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.buslaev.coderclicker.ClickerApplication.Companion.globalLanguagesShop
import com.buslaev.coderclicker.other.Constants.APP_LANGUAGES_PURCHESED
import com.buslaev.coderclicker.other.Constants.APP_LANGUAGES_REMAINED
import com.buslaev.coderclicker.other.Constants.SHOP_STORAGE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShopSharedPreferences(context: Context) : CoderSharedPreferences {

    private val mSharedPreferences = context.getSharedPreferences(SHOP_STORAGE, MODE_PRIVATE)
    private val mEditor = mSharedPreferences.edit()
    private val mGson = Gson()

    override fun putData() {
        val dataLanguages = mGson.toJson(globalLanguagesShop)
        mEditor.apply {
            putString(APP_LANGUAGES_REMAINED, dataLanguages)
        }.apply()
    }

    override fun getData() {
        val dataLanguages = mSharedPreferences.getString(APP_LANGUAGES_REMAINED, "")
        val type = object : TypeToken<HashMap<String, Int>>() {}.type
        val hashMapOfLanguages = mGson.fromJson<HashMap<String, Int>>(dataLanguages, type)
        globalLanguagesShop = hashMapOfLanguages ?: hashMapOf()
    }
}