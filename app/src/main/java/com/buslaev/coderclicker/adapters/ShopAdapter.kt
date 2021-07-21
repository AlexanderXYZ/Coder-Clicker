package com.buslaev.coderclicker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.buslaev.coderclicker.ClickerApplication.Companion.globalClickCode
import com.buslaev.coderclicker.ClickerApplication.Companion.globalCodesPerClick
import com.buslaev.coderclicker.ClickerApplication.Companion.globalLanguagesShop
import com.buslaev.coderclicker.R
import com.buslaev.coderclicker.models.ShopModel
import kotlinx.android.synthetic.main.shop_item.view.*

class ShopAdapter(
    private val globalShop: HashMap<String, Boolean>
) : RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {

    private var mList = emptyList<ShopModel>()

    inner class ShopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item, parent, false)
        return ShopViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val currentItem = mList[position]
        holder.itemView.apply {
            title_shop_item.text = currentItem.title
            growth_shop_item.text = currentItem.growth
            if (currentItem.purchased) {
                button_buy_shop_item.apply {
                    isEnabled = false
                    text = context.getString(R.string.shop_item_purchased)
                }
            } else {
                button_buy_shop_item.text = "$" + currentItem.price
            }
            button_buy_shop_item.setOnClickListener {
                globalClickCode -= currentItem.price.toInt()
                globalCodesPerClick += currentItem.growth.toInt()
                globalShop[currentItem.imageUrl] = true
            }

            Glide
                .with(this)
                .load(currentItem.imageUrl)
                .into(image_shop_item)
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