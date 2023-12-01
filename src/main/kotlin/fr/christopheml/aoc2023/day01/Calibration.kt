package fr.christopheml.aoc2023.day01

import fr.christopheml.aoc2023.common.runners.Solution
import fr.christopheml.aoc2023.common.Input

class Calibration: Solution<Int>(1) {

    private val textNumbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        .mapIndexed { index, number -> number to index + 1 }
        .toMap()

    override fun partOne(input: Input): Int {
        return input.multi
            .asSequence()
            .map { digitize(it) }
            .sum()
    }

    override fun partTwo(input: Input): Int {
        return input.multi
            .asSequence()
            .map { digitizeWithText(it) }
            .sum()
    }

    private fun digitize(input: String): Int {
        val digits = input.filter { it.isDigit() }
        return digits.first().digitToInt() * 10 + digits.last().digitToInt()
    }

    private fun digitizeWithText(input: String): Int {
        return firstDigit(input) * 10 + lastDigit(input)
    }

    private fun firstDigit(input: String): Int {
        for (i in input.indices) {
            val digit = getDigit(input, i)
            if (digit != null) return digit
        }
        throw IllegalStateException()
    }

    private fun lastDigit(input: String): Int {
        for (i in input.indices.reversed()) {
            val digit = getDigit(input, i)
            if (digit != null) return digit
        }
        throw IllegalStateException()
    }

    private fun getDigit(input: String, index: Int): Int? {
        if (input[index].isDigit()) {
            return input[index].digitToInt()
        }
        textNumbers.keys.forEach {
            if (input.startsWith(it, index)) return textNumbers[it]
        }
        return null
    }

}

fun main() {
    Calibration().run()
}
