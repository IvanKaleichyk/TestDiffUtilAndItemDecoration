package com.koleychik.testdiffutilanditemdecoration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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

        findViewById<Button>(R.id.btnInsert).setOnClickListener {
            val newList = mutableListOf<MainModel>()
            newList.addAll(adapter.list)
            for (i in (0 until 3)) newList.add(repository.createModel())
            adapter.updateList(newList)
        }
        findViewById<Button>(R.id.btnInsertWithoutDiffUtil).setOnClickListener {
            adapter.insertWithout(repository.getList())
        }
        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            adapter.delete(adapter.list[0])
        }
    }

    private fun createItemDecoration(){

    }

}