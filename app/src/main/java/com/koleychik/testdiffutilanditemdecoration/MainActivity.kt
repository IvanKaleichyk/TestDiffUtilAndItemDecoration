package com.koleychik.testdiffutilanditemdecoration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val recyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = MainRepository()

        val adapter = MainAdapter()
        recyclerView.adapter = adapter
        createItemDecoration()

        findViewById<Button>(R.id.btnInsert).setOnClickListener {
            val newList = mutableListOf<MainModel>()
            newList.addAll(adapter.list)
            for (i in (0 until 3)) newList.add(repository.createModel())
            adapter.updateList(newList)
        }
        findViewById<Button>(R.id.btnInsertWithoutDiffUtil).setOnClickListener {
            adapter.insertWithout(repository.getList())
        }
    }

    private fun createItemDecoration() {
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                LinearLayoutManager.VERTICAL
            )
//                .apply {
//                    setDrawable(
//                        ContextCompat.getDrawable(
//                            applicationContext,
//                            R.drawable.simple_item_decoration
//                        )!!
//                    )
//                }
        )

//        recyclerView.addItemDecoration(
//            DividerItemDecoration(
//                applicationContext,
//                LinearLayoutManager.VERTICAL
//            )
//                .apply {
//                    setDrawable(
//                        ContextCompat.getDrawable(
//                            applicationContext,
//                            R.drawable.simple_item_decoration
//                        )!!
//                    )
//                })
    }

}