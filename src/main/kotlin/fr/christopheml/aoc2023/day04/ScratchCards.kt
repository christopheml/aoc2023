package fr.christopheml.aoc2023.day04

import fr.christopheml.aoc2023.common.Input
import fr.christopheml.aoc2023.common.StringSplitter.Companion.one
import fr.christopheml.aoc2023.common.asInts
import fr.christopheml.aoc2023.common.pow
import fr.christopheml.aoc2023.common.runners.Solution
import fr.christopheml.aoc2023.common.split


class ScratchCard(input: String) {

    val matchCount: Int

    init {
        val (_, winning, actual) = input.split(one(": "), one(" | "))
        val cardNumbers = actual.asInts(' ').toSet()
        val winningNumbers = winning.asInts(' ').toSet()
        matchCount = cardNumbers.count { winningNumbers.contains(it) }
    }

    fun score(): Int = if (matchCount == 0) 0 else 2.pow(matchCount - 1)

}

class ScratchCards : Solution<Int>(4) {

    override fun partOne(input: Input): Int {
        return input.multi.asSequence()
            .map { ScratchCard(it) }
            .sumOf { it.score() }
    }

    override fun partTwo(input: Input): Int {
        val cardMatchCount = input.multi.asSequence()
            .map { ScratchCard(it).matchCount }
            .toList()
        val cardCount = cardMatchCount.map { 1 }.toMutableList()

        cardMatchCount.forEachIndexed { i, score ->
            for (n in i + 1 .. i + score) {
                if (n < cardCount.size) cardCount[n] += cardCount[i]
            }
        }

        return cardCount.sum()
    }

}

fun main() {
    ScratchCards().run()
}
