package com.buslaev.coderclicker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import com.buslaev.coderclicker.R
import com.buslaev.coderclicker.databinding.FragmentMainBinding
import com.buslaev.coderclicker.viewModels.mainViewModel.MainViewModel


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: MainViewModel

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
        val dropDownAnim = AnimationUtils.loadAnimation(context, R.anim.drop_down_code)
        mBinding.screenCl.setOnClickListener {
            mViewModel.increaseCounter()
            mBinding.animTextView.startAnimation(dropDownAnim)
        }
    }

    override fun onResume() {
        super.onResume()
        mViewModel.setCodes()
    }

}