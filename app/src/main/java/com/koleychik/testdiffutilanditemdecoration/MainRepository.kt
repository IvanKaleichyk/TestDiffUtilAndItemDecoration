package com.koleychik.testdiffutilanditemdecoration

import java.util.*

class MainRepository {

    private val random = Random()

    fun getList(): List<MainModel> {
        val value = random.nextInt(10)
        val list = mutableListOf<MainModel>()
        for (i in (0 until value)) list.add(createModel())
        return list
    }

    fun createModel() = MainModel(
        random.nextInt(100),
        createText()
    )

    private fun createText(): String {
        var string = ""
        for (i in (1..5)) string += random.nextInt(10)
        return string
    }

}