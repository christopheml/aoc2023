package com.github.christopheml.aoc2022.common.runners

import com.github.christopheml.aoc2022.common.Input

abstract class Solution<Out>(private val day: Int) {

    abstract fun partOne(input: Input): Out
    abstract fun partTwo(input: Input): Out

    fun run() {
        println("Solution for day %02d".format(day))
        val input = Input(day)
        runPart(1, input, this::partOne)
        runPart(2, input, this::partTwo)
    }

    private fun runPart(number: Int, input: Input, runnable: (Input) -> Out) {
        try {
            val timer = Timer()
            val result = runnable.invoke(input)
            val time = timer.readString()

            val representation = when (result) {
                null -> "<no result>"
                is String -> result
                else -> result.toString()
            }
            println("\tPart %d (time: %s): %s".format(number, time, representation))
        } catch (_: NotImplementedError) {
            println("\tPart %d: <not implemented>".format(number))
        }
    }

}
