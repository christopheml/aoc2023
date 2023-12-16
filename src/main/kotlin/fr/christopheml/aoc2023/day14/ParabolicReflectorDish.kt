package fr.christopheml.aoc2023.day14

import fr.christopheml.aoc2023.common.Grid
import fr.christopheml.aoc2023.common.Input
import fr.christopheml.aoc2023.common.runners.Solution
import fr.christopheml.aoc2023.common.splitKeepingEmpty

class ParabolicReflectorDish : Solution<Int>(14) {

    fun roll(rocks: List<Char>): List<Char> {
        return rocks.splitKeepingEmpty('#').map {
            it.sortedDescending()
        }.joinToString("#") { it.joinToString("") }.toList()
    }

    fun score(rocks: List<Char>): Int {
        val score = rocks.mapIndexed { i, rock -> if (rock == 'O') rocks.size - i else 0 }.sum()

        return score;
    }

    override fun partOne(input: Input): Int {
        val rocks = Grid(input.multi.asList().map { it.toList() }.toList()).transpose()
        return rocks.lines.asSequence().map { roll(it) }.sumOf { score(it) }
    }

    override fun partTwo(input: Input): Int {
        TODO("Not yet implemented")
    }
}

fun main() {
    ParabolicReflectorDish().run()
}
