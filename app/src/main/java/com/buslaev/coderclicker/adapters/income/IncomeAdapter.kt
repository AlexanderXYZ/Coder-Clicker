package com.buslaev.coderclicker.adapters.income

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.buslaev.coderclicker.R
import com.buslaev.coderclicker.models.ShopModel
import com.buslaev.coderclicker.other.Income

class IncomeAdapter(
    private val context: Context
) : RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder>() {

    private var mList = emptyList<ShopModel>()

    inner class IncomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_shop_item)
        val growth: TextView = itemView.findViewById(R.id.growth_shop_item)
        val remained: TextView = itemView.findViewById(R.id.remained_shop_item)
        val buyItem: TextView = itemView.findViewById(R.id.button_buy_shop_item)
        val imageItem: ImageView = itemView.findViewById(R.id.image_shop_item)
        val imageSoldOut: ImageView = itemView.findViewById(R.id.image_sold_out_shop_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item, parent, false)
        return IncomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncomeViewHolder, position: Int) {
        val currentItem = mList[position]
        val currentIncomeItem = CurrentIncomeItem(currentItem)
        holder.apply {
            title.text = currentIncomeItem.getTitle()
            growth.text = currentIncomeItem.getGrowth()
            remained.text = currentIncomeItem.getRemained()
            if (currentIncomeItem.remainedEqualZero()) {
                buyItem.apply {
                    holder.imageSoldOut.visibility = View.VISIBLE
                    isEnabled = false
                }
            } else {
                val price = currentIncomeItem.getPrice()
                buyItem.text = price
            }
            buyItem.setOnClickListener {
                if (currentIncomeItem.globalClickMorePrice()) {
                    currentIncomeItem.setGlobalVariables()
                    currentItem.remained = currentIncomeItem.getRemained()
                    notifyItemChanged(position)
                } else {
                    Toast.makeText(context, "Not enough click", Toast.LENGTH_SHORT).show()
                }
            }
            Glide
                .with(itemView)
                .load(currentIncomeItem.getImageUrl())
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