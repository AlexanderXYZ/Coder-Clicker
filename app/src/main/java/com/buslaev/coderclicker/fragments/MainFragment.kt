package com.buslaev.coderclicker.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.buslaev.coderclicker.MainActivity
import com.buslaev.coderclicker.R
import com.buslaev.coderclicker.databinding.FragmentMainBinding
import com.buslaev.coderclicker.viewModels.mainViewModel.MainViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: MainViewModel

    private lateinit var listOfTextView: List<TextView>
    private lateinit var arrayOfCodes: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        mBinding.lifecycleOwner = this
        mBinding.viewModel = mViewModel
        return mBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLists()
        initButtons()
    }

    private fun initLists() {
        listOfTextView = listOf(
            mBinding.anim1TextView,
            mBinding.anim2TextView,
            mBinding.anim3TextView,
            mBinding.anim4TextView,
            mBinding.anim5TextView,
            mBinding.anim6TextView,
            mBinding.anim7TextView,
            mBinding.anim8TextView,
            mBinding.anim9TextView,
            mBinding.anim10TextView
        )
        arrayOfCodes = resources.getStringArray(R.array.codes_array)
    }

    private fun initButtons() {
        mBinding.screenCl.setOnClickListener {
            mViewModel.increaseCounter()
            val randTextView = getRandomNumber(until = listOfTextView.size - 1)
            val randCode = getRandomNumber(until = arrayOfCodes.size - 1)

            val textView = listOfTextView[randTextView]
            textView.text = arrayOfCodes[randCode]

            runBlocking { startAnim(textView) }
        }
    }

    private fun getRandomNumber(from: Int = 0, until: Int): Int {
        return Random.nextInt(from, until)
    }

    private suspend fun startAnim(textView: TextView) = coroutineScope {
        launch {
            val dropDownAnim = AnimationUtils.loadAnimation(context, R.anim.drop_down_code)
            textView.startAnimation(dropDownAnim)
        }
    }

    override fun onResume() {
        super.onResume()
        mViewModel.setCodes()
    }

}