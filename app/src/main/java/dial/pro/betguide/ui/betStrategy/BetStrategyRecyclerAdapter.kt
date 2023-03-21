package dial.pro.betguide.ui.betStrategy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dial.pro.betguide.R

class BetStrategyRecyclerAdapter(private val itemClickListener: BetStrategyItemListener) :
    ListAdapter<String, BetStrategyRecyclerAdapter.BetStrategyVH>(
        BetStrategyDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BetStrategyVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_layout, parent, false)

        return BetStrategyVH(view)
    }

    override fun onBindViewHolder(holder: BetStrategyVH, position: Int) {
        holder.bind(currentList[position])
    }

    inner class BetStrategyVH(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: String) {
            val tw = itemView.findViewById<TextView>(R.id.tw_title)
            tw.text = item

            itemView.setOnClickListener {
                itemClickListener.onItemClick(item)
            }
        }
    }
}

object BetStrategyDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}