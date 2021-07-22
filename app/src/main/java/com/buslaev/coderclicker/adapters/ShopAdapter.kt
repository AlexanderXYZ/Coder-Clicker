package com.buslaev.coderclicker.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.buslaev.coderclicker.ClickerApplication.Companion.globalClickCode
import com.buslaev.coderclicker.ClickerApplication.Companion.globalCodesPerClick
import com.buslaev.coderclicker.ClickerApplication.Companion.globalLanguagesShop
import com.buslaev.coderclicker.R
import com.buslaev.coderclicker.models.ShopModel
import com.buslaev.coderclicker.other.Constants
import com.buslaev.coderclicker.other.Shops
import kotlinx.android.synthetic.main.shop_item.view.*
import kotlin.math.pow
import kotlin.math.roundToInt

class ShopAdapter(
    private val shop: Shops,
    private val context: Context
) : RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {

    private var mList = emptyList<ShopModel>()

    inner class ShopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_shop_item)
        val growth: TextView = itemView.findViewById(R.id.growth_shop_item)
        val remained: TextView = itemView.findViewById(R.id.remained_shop_item)
        val buyItem: TextView = itemView.findViewById(R.id.button_buy_shop_item)
        val imageItem: ImageView = itemView.findViewById(R.id.image_shop_item)
        val imageSoldOut: ImageView = itemView.findViewById(R.id.image_sold_out_shop_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item, parent, false)
        return ShopViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val currentItem = mList[position]
        val currentShopItem = CurrentShopItem(currentItem, shop)

        holder.apply {
            title.text = currentShopItem.getTitle()
            growth.text = currentShopItem.getGrowth()
            remained.text = currentShopItem.getRemained()
            if (currentShopItem.remainedEqualZero()) {
                buyItem.apply {
                    holder.imageSoldOut.visibility = View.VISIBLE
                    isEnabled = false
                }
            } else {
                val price = currentShopItem.getPrice()
                buyItem.text = price
            }
            buyItem.setOnClickListener {
                if (currentShopItem.globalMoneyMorePriceItem()) {
                    currentShopItem.setGlobalVariables()
                    currentItem.remained = currentShopItem.getRemained()
                    notifyItemChanged(position)
                } else {
                    Toast.makeText(context, "Not enough money", Toast.LENGTH_SHORT).show()
                }
            }
            Glide
                .with(itemView)
                .load(currentShopItem.getImageUrl())
                .into(imageItem)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setList(list: List<ShopModel>) {
        mList = list
        notifyDataSetChanged()
    }
}