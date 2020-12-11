package com.koleychik.testdiffutilanditemdecoration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import java.lang.Exception

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var list = mutableListOf<MainModel>()

    fun delete(model: MainModel) {
        val newList = mutableListOf<MainModel>()
        newList.addAll(list)
        newList.remove(model)

        updateList(newList)
    }

    fun insertWithout(newList: List<MainModel>) {
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

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: MainModel) {
            CoroutineScope(Dispatchers.Default).launch {
                delay(500)
                withContext(Dispatchers.Main) {
                    itemView.apply {
                        findViewById<TextView>(R.id.number).text = model.id.toString()
                        findViewById<TextView>(R.id.text).text = model.text
                        setOnClickListener {
                            startAnimDelete(model)
                        }
                    }
                }
            }
        }

        private fun startAnimDelete(model: MainModel) {
            val anim = AnimationUtils.loadAnimation(itemView.context, R.anim.item_rv_move_to_left)
            anim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {}

                override fun onAnimationEnd(p0: Animation?) {
                    delete(model)
                }

                override fun onAnimationRepeat(p0: Animation?) {}

            })
            itemView.startAnimation(anim)
        }
    }
}