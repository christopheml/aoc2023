package fr.christopheml.aoc2023.day01

import fr.christopheml.aoc2023.common.runners.Solution
import fr.christopheml.aoc2023.common.Input

class Calibration : Solution<Int>(1) {

    private val literals = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        .mapIndexed { index, number -> number to index + 1 }
        .toMap()

    override fun partOne(input: Input): Int {
        return input.multi.asSequence()
            .map { findDigit(it, it.indices) * 10 + findDigit(it, it.indices.reversed()) }
            .sum()
    }

    override fun partTwo(input: Input): Int {
        return input.multi.asSequence()
            .map { findDigit(it, it.indices, literals) * 10 + findDigit(it, it.indices.reversed(), literals) }
            .sum()
    }

    private fun findDigit(input: String, indices: IntProgression, numbersMapping: Map<String, Int> = emptyMap()): Int {
        for (i in indices) {
            if (input[i].isDigit()) {
                return input[i].digitToInt()
            }
            numbersMapping.keys.forEach {
                if (input.startsWith(it, i)) return numbersMapping[it]!!
            }
        }
        throw IllegalStateException()
    }

}

fun main() {
    Calibration().run()
}
