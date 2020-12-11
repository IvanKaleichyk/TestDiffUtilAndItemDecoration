package com.koleychik.testdiffutilanditemdecoration

import androidx.recyclerview.widget.DiffUtil

class MainDiffUtil(private val newList: List<MainModel>, private val oldList: List<MainModel>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition].id == oldList[oldItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition] == oldList[oldItemPosition]


}