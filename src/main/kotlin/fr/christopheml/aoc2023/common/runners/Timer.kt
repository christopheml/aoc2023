package fr.christopheml.aoc2023.common.runners

class Timer {

    private val start: Long = System.currentTimeMillis()

    fun read(): Long {
        return System.currentTimeMillis() - start
    }

    fun readString(): String {
        val millis = read()
        val seconds = millis / 1000
        if (seconds < 1) return "%dms".format(millis)
        if (seconds < 60) return "%d,%ds".format(seconds, millis % 1000 / 100)
        return "%dm%ds".format(seconds / 60, seconds % 60)
    }

}
