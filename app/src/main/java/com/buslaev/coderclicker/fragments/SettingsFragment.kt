package com.buslaev.coderclicker.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.buslaev.coderclicker.ClickerApplication.Companion.globalClickCode
import com.buslaev.coderclicker.ClickerApplication.Companion.globalCodesPerClick
import com.buslaev.coderclicker.ClickerApplication.Companion.globalComponentsShop
import com.buslaev.coderclicker.ClickerApplication.Companion.globalIncomeShop
import com.buslaev.coderclicker.ClickerApplication.Companion.globalLanguagesShop
import com.buslaev.coderclicker.ClickerApplication.Companion.globalMoney
import com.buslaev.coderclicker.ClickerApplication.Companion.globalMoneyPerSecond
import com.buslaev.coderclicker.ClickerApplication.Companion.globalProgramsShop
import com.buslaev.coderclicker.R
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reset_progress_button.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
                .setTitle("Reset progress?")
                .setMessage("This will reset the progress to the initial level")
                .setPositiveButton("Yes") { dialog, which ->
                    resetProgress {
                        Toast.makeText(activity, "Progress was deleted", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton("No") { dialog, which -> }
            builder.show()
        }
    }

    private fun resetProgress(onSuccess: () -> Unit) {
        globalClickCode = 0
        globalMoney = 0
        globalCodesPerClick = 1
        globalMoneyPerSecond = 0

        globalIncomeShop = hashMapOf()
        globalLanguagesShop = hashMapOf()
        globalComponentsShop = hashMapOf()
        globalProgramsShop = hashMapOf()
        onSuccess()
    }
}