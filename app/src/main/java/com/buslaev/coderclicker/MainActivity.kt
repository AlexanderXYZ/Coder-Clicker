package com.buslaev.coderclicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.buslaev.coderclicker.sharedPreference.ShopSharedPreferences
import com.buslaev.coderclicker.sharedPreference.StatsSharedPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private lateinit var mStatsSharedPreferences: StatsSharedPreferences
    private lateinit var mShopSharedPreferences: ShopSharedPreferences
    private var destinationShop: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupGlobalVariables()

        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupGlobalVariables() {
        mStatsSharedPreferences = StatsSharedPreferences(this)
        mStatsSharedPreferences.getData()
        mShopSharedPreferences = ShopSharedPreferences(this)
        mShopSharedPreferences.getData()
    }

    override fun onStop() {
        super.onStop()
        mStatsSharedPreferences.putData()
        mShopSharedPreferences.putData()
    }

    private fun setupNavigation() {
        navController = findNavController(R.id.fragmentContainerView)
        bottom_nav_view.setupWithNavController(navController)
        bottom_nav_view.setOnApplyWindowInsetsListener(null)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.languagesFragment -> {
                    if (navigateBackOrNot(R.id.languagesFragment)) {
                        navController.navigate(R.id.action_languagesFragment_to_mainFragment)
                    }
                }
                R.id.programsFragment -> {
                    if (navigateBackOrNot(R.id.programsFragment)) {
                        navController.navigate(R.id.action_programsFragment_to_mainFragment)
                    }
                }
                R.id.componentsFragment -> {
                    if (navigateBackOrNot(R.id.componentsFragment)) {
                        navController.navigate(R.id.action_componentsFragment_to_mainFragment)
                    }
                }
                R.id.incomeFragment -> {
                    if (navigateBackOrNot(R.id.incomeFragment)) {
                        navController.navigate(R.id.action_incomeFragment_to_mainFragment)
                    }
                }
            }
        }
    }

    private fun navigateBackOrNot(fragment: Int): Boolean {
        return if (destinationShop == fragment) {
            destinationShop = 0
            true
        } else {
            destinationShop = fragment
            false
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}