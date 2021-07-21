package com.buslaev.coderclicker.sharedPreference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.buslaev.coderclicker.ClickerApplication.Companion.globalLanguagesShop
import com.buslaev.coderclicker.other.Constants.APP_LANGUAGES_PURCHESED
import com.buslaev.coderclicker.other.Constants.SHOP_STORAGE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShopSharedPreferences(context: Context) : CoderSharedPreferences {

    private val mSharedPreferences = context.getSharedPreferences(SHOP_STORAGE, MODE_PRIVATE)
    private val mEditor = mSharedPreferences.edit()
    private val mGson = Gson()

    override fun putData() {
        val data = mGson.toJson(globalLanguagesShop)
        mEditor.putString(APP_LANGUAGES_PURCHESED, data).apply()
    }

    override fun getData() {
        val data = mSharedPreferences.getString(APP_LANGUAGES_PURCHESED, "")
        val type = object : TypeToken<HashMap<String, Boolean>>() {}.type
        val hashMap = mGson.fromJson<HashMap<String, Boolean>>(data, type)
        globalLanguagesShop = hashMap ?: hashMapOf()
    }
}