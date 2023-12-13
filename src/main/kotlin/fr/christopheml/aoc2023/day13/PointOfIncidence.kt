package fr.christopheml.aoc2023.day13

import fr.christopheml.aoc2023.common.Grid
import fr.christopheml.aoc2023.common.Input
import fr.christopheml.aoc2023.common.runners.Solution
import fr.christopheml.aoc2023.common.split

class PointOfIncidence : Solution<Int>(13) {

    private fun findLineOfReflection(grid: Grid<Char>, allowedSmudges: Int = 0): Int {
        for (n in grid.lines.indices.first..<grid.lines.indices.last) {
            if (differencesFromThisLine(n, grid.lines) == allowedSmudges) return n + 1
        }
        return 0
    }

    private fun <T> differencesFromThisLine(index: Int, items: List<List<T>>) = sequence {
        for (n in 0..items.size) {
            val line1 = index - n
            val line2 = index + n + 1
            if (line1 < 0 || line2 >= items.size) break
            for (i in items[line1].indices) {
                if (items[line1][i] != items[line2][i]) yield(1)
            }
        }
    }.sum()

    override fun partOne(input: Input): Int {
        return input.multi.asList()
            .split("")
            .map { Grid(it.map { line -> line.toList() }) }
            .sumOf { 100 * findLineOfReflection(it) + findLineOfReflection(it.transpose()) }
    }

    override fun partTwo(input: Input): Int {
        return input.multi.asList()
            .split("")
            .map { Grid(it.map { line -> line.toList() }) }
            .sumOf { 100 * findLineOfReflection(it, 1) + findLineOfReflection(it.transpose(), 1) }
    }
}

fun main() {
    PointOfIncidence().run()
}
