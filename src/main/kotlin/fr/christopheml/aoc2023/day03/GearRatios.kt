package fr.christopheml.aoc2023.day03

import fr.christopheml.aoc2023.common.Input
import fr.christopheml.aoc2023.common.runners.Solution

data class Coordinate(val line: Int, val column: Int)

class EngineSchematic(private val data: List<String>) {

    private val numbersRe = "(\\d)+".toRegex()
    private val width = data[0].length
    private val height = data.size

    fun partNumbers(): List<Int> {
        return data.flatMapIndexed { lineNumber, lineContent ->
            numbersRe.findAll(lineContent)
                .filter { hasNearbySymbol(lineNumber, it.range) }
                .map { it.value.toInt() }
        }
    }

    fun gears(): Map<Coordinate, List<Int>> =
        data.flatMapIndexed { lineNumber, lineContent ->
            numbersRe.findAll(lineContent)
                .map {
                    Pair(
                        it.value.toInt(),
                        nearby(lineNumber, it.range).filter { coord -> isGear(coord.line, coord.column) }.firstOrNull()
                    )
                }.filter { it.second != null }
        }
            .groupBy({ it.second!! }, { it.first })
            .filterValues { it.size == 2 }
    private fun nearby(line: Int, range: IntRange) = iterator {
        val extendedRange = range.first - 1..range.last + 1
        yield(Coordinate(line, range.first - 1))
        yield(Coordinate(line, range.last + 1))
        extendedRange.forEach {
            yield(Coordinate(line - 1, it))
            yield(Coordinate(line + 1, it))
        }
    }.asSequence()

    private fun hasNearbySymbol(line: Int, range: IntRange) = nearby(line, range).any { isSymbol(it.line, it.column) }

    private fun inRange(line: Int, column: Int) = line in 0..<height && column in 0 ..<width

    private fun isSymbol(line: Int, column: Int) =
        inRange(line, column) && data[line][column] != '.' && !data[line][column].isDigit()

    private fun isGear(line: Int, column: Int) = inRange(line, column) && data[line][column] == '*'
}

class GearRatios: Solution<Int>(3) {
    override fun partOne(input: Input): Int = EngineSchematic(input.multi.asList()).partNumbers().sum()

    override fun partTwo(input: Input): Int {
        val gears = EngineSchematic(input.multi.asList()).gears()
        return gears.values.sumOf { it[0] * it[1] }
    }

}

fun main() {
    GearRatios().run()
}