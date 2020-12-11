package com.koleychik.testdiffutilanditemdecoration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var list = mutableListOf<MainModel>()

    fun insertWithout(newList: List<MainModel>){
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun insertList(newList: List<MainModel>) {
        val diffUtil = MainDiffUtil(newList, list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        list.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateList(newList: List<MainModel>) {
        val diffUtil = MainDiffUtil(newList, list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        list.clear()
        list.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_rv, parent, false
            )
        )


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: MainModel) {
            itemView.apply {
                findViewById<TextView>(R.id.number).text = model.id.toString()
                findViewById<TextView>(R.id.text).text = model.text
            }
        }
    }
}