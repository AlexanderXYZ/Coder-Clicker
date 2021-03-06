package com.buslaev.coderclicker.fragments.shops

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buslaev.coderclicker.ClickerApplication.Companion.globalComponentsShop
import com.buslaev.coderclicker.R
import com.buslaev.coderclicker.adapters.shop.ShopAdapter
import com.buslaev.coderclicker.models.ShopModel
import com.buslaev.coderclicker.other.Constants.COMPONENTS_TYPE
import com.buslaev.coderclicker.other.Shops.COMPONENTS
import com.buslaev.coderclicker.viewModels.shopViewModel.ShopViewModel
import com.buslaev.coderclicker.viewModels.shopViewModel.ShopViewModelFactory
import kotlinx.android.synthetic.main.fragment_components.*


class ComponentsFragment : Fragment() {

    private lateinit var mAdapter: ShopAdapter
    private lateinit var mRecyclerView: RecyclerView

    private lateinit var mViewModel: ShopViewModel
    private lateinit var mObserver: Observer<List<ShopModel>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_components, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(
            this,
            ShopViewModelFactory(COMPONENTS_TYPE)
        ).get(ShopViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mAdapter = ShopAdapter(COMPONENTS,requireContext())
        mRecyclerView = components_recyclerView
        mRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        mObserver = Observer { list ->
            val sortedList = list.sortedBy { it.id }
            for (item in sortedList) {
                if (globalComponentsShop.containsKey(item.imageUrl)) {
                    item.remained = globalComponentsShop[item.imageUrl].toString()
                }
            }
            mAdapter.setList(sortedList)
        }
        mViewModel.componentsData.observe(viewLifecycleOwner, mObserver)
    }
}