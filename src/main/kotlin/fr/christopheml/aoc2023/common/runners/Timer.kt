package com.github.christopheml.aoc2022.common.runners

class Timer {

    private val start: Long = System.currentTimeMillis()

    fun read(): Long {
        return System.currentTimeMillis() - start
    }

    fun readString(): String {
        val millis = read()
        val seconds = millis / 60
        if (seconds < 60) return "%d,%ds".format(seconds, millis % 1000 / 100)
        return "%dm%ds".format(seconds / 60, seconds % 60)
    }

}
