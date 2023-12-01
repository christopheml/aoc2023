package fr.christopheml.aoc2023.day01

import fr.christopheml.aoc2023.common.Input
import com.github.christopheml.aoc2022.common.runners.Solution

class Calibration: Solution<Int>(1) {

    override fun partOne(input: Input): Int {
        return input.multi
            .asSequence()
            .map { simpleDigitIterable(it) }
            .map { digitize(it) }
            .sum()
    }

    override fun partTwo(input: Input): Int {
        return input.multi
            .asSequence()
            .map { textDigitIterable(it) }
            .map { digitize(it) }
            .sum()
    }

    private fun digitize(input: Iterable<Int>): Int {
        return input.first() * 10 + input.last()
    }

    private fun simpleDigitIterable(input: String) = Iterable {
        input.filter { it.isDigit() }.map { it.digitToInt() }.iterator()
    }

    private fun textDigitIterable(input: String) = Iterable {
        iterator {
            for (i in input.indices) {
                if (input[i].isDigit()) {
                    yield(input[i].digitToInt())
                    continue
                }
                if (i <= input.length - 5) {
                    if (input.startsWith("three", i)) { yield(3); continue }
                    if (input.startsWith("seven", i)) { yield(7); continue }
                    if (input.startsWith("eight", i)) { yield(8); continue }
                }
                if (i <= input.length - 4) {
                    if (input.startsWith("four", i)) { yield(4); continue }
                    if (input.startsWith("five", i)) { yield(5); continue }
                    if (input.startsWith("nine", i)) { yield(9); continue }
                }
                if (i <= input.length - 3) {
                    if (input.startsWith("one", i)) { yield(1); continue }
                    if (input.startsWith("two", i)) { yield(2); continue }
                    if (input.startsWith("six", i)) { yield(6); continue }
                }
            }
        }
    }

}

fun main() {
    Calibration().run()
}
